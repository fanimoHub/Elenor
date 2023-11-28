
package com.fanimo.ecommerce.core.data.repository

import com.fanimo.ecommerce.core.data.Synchronizer
import com.fanimo.ecommerce.core.data.changeListSync
import com.fanimo.ecommerce.core.data.model.asEntity
import com.fanimo.ecommerce.core.data.model.topicCrossReferences
import com.fanimo.ecommerce.core.data.model.topicEntityShells
import com.fanimo.ecommerce.core.database.dao.NewsResourceDao
import com.fanimo.ecommerce.core.database.dao.TopicDao
import com.fanimo.ecommerce.core.database.model.PopulatedNewsResource
import com.fanimo.ecommerce.core.database.model.TopicEntity
import com.fanimo.ecommerce.core.database.model.asExternalModel
import com.fanimo.ecommerce.core.datastore.ChangeListVersions
import com.fanimo.ecommerce.core.datastore.ElePreferencesDataSource
import com.fanimo.ecommerce.core.model.data.NewsResource
import com.fanimo.ecommerce.core.network.EleNetworkDataSource
import com.fanimo.ecommerce.core.network.model.NetworkNewsResource
import com.fanimo.ecommerce.core.notifications.Notifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Heuristic value to optimize for serialization and deserialization cost on client and server
// for each news resource batch.
private const val SYNC_BATCH_SIZE = 40

/**
 * Disk storage backed implementation of the [NewsRepository].
 * Reads are exclusively from local storage to support offline access.
 */
class OfflineFirstNewsRepository @Inject constructor(
    private val elePreferencesDataSource: ElePreferencesDataSource,
    private val newsResourceDao: NewsResourceDao,
    private val topicDao: TopicDao,
    private val network: EleNetworkDataSource,
    private val notifier: Notifier,
) : NewsRepository {

    override fun getNewsResources(
        query: NewsResourceQuery,
    ): Flow<List<NewsResource>> = newsResourceDao.getNewsResources(
        useFilterTopicIds = query.filterTopicIds != null,
        filterTopicIds = query.filterTopicIds ?: emptySet(),
        useFilterNewsIds = query.filterNewsIds != null,
        filterNewsIds = query.filterNewsIds ?: emptySet(),
    )
        .map { it.map(PopulatedNewsResource::asExternalModel) }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean {
        var isFirstSync = false
        return synchronizer.changeListSync(
            versionReader = ChangeListVersions::newsResourceVersion,
            changeListFetcher = { currentVersion ->
                isFirstSync = currentVersion <= 0
                network.getNewsResourceChangeList(after = currentVersion)
            },
            versionUpdater = { latestVersion ->
                copy(newsResourceVersion = latestVersion)
            },
            modelDeleter = newsResourceDao::deleteNewsResources,
            modelUpdater = { changedIds ->
                val userData = elePreferencesDataSource.userData.first()
                val hasOnboarded = userData.shouldHideOnboarding
                val followedTopicIds = userData.followedTopics

                val existingNewsResourceIdsThatHaveChanged = when {
                    hasOnboarded -> newsResourceDao.getNewsResourceIds(
                        useFilterTopicIds = true,
                        filterTopicIds = followedTopicIds,
                        useFilterNewsIds = true,
                        filterNewsIds = changedIds.toSet(),
                    )
                        .first()
                        .toSet()
                    // No need to retrieve anything if notifications won't be sent
                    else -> emptySet()
                }

                if (isFirstSync) {
                    // When we first retrieve news, mark everything viewed, so that we aren't
                    // overwhelmed with all historical news.
                    elePreferencesDataSource.setNewsResourcesViewed(changedIds, true)
                }

                // Obtain the news resources which have changed from the network and upsert them locally
                changedIds.chunked(SYNC_BATCH_SIZE).forEach { chunkedIds ->
                    val networkNewsResources = network.getNewsResources(ids = chunkedIds)

                    // Order of invocation matters to satisfy id and foreign key constraints!

                    topicDao.insertOrIgnoreTopics(
                        topicEntities = networkNewsResources
                            .map(NetworkNewsResource::topicEntityShells)
                            .flatten()
                            .distinctBy(TopicEntity::id),
                    )
                    newsResourceDao.upsertNewsResources(
                        newsResourceEntities = networkNewsResources.map(
                            NetworkNewsResource::asEntity,
                        ),
                    )
                    newsResourceDao.insertOrIgnoreTopicCrossRefEntities(
                        newsResourceTopicCrossReferences = networkNewsResources
                            .map(NetworkNewsResource::topicCrossReferences)
                            .distinct()
                            .flatten(),
                    )
                }

                if (hasOnboarded) {
                    val addedNewsResources = newsResourceDao.getNewsResources(
                        useFilterTopicIds = true,
                        filterTopicIds = followedTopicIds,
                        useFilterNewsIds = true,
                        filterNewsIds = changedIds.toSet() - existingNewsResourceIdsThatHaveChanged,
                    )
                        .first()
                        .map(PopulatedNewsResource::asExternalModel)

                    if (addedNewsResources.isNotEmpty()) {
                        notifier.postNewsNotifications(
                            newsResources = addedNewsResources,
                        )
                    }
                }
            },
        )
    }
}
