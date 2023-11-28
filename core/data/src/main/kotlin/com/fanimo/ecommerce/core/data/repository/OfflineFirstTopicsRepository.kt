
package com.fanimo.ecommerce.core.data.repository

import com.fanimo.ecommerce.core.data.Synchronizer
import com.fanimo.ecommerce.core.data.changeListSync
import com.fanimo.ecommerce.core.data.model.asEntity
import com.fanimo.ecommerce.core.database.dao.TopicDao
import com.fanimo.ecommerce.core.database.model.TopicEntity
import com.fanimo.ecommerce.core.database.model.asExternalModel
import com.fanimo.ecommerce.core.datastore.ChangeListVersions
import com.fanimo.ecommerce.core.model.data.Topic
import com.fanimo.ecommerce.core.network.EleNetworkDataSource
import com.fanimo.ecommerce.core.network.model.NetworkTopic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Disk storage backed implementation of the [TopicsRepository].
 * Reads are exclusively from local storage to support offline access.
 */
class OfflineFirstTopicsRepository @Inject constructor(
    private val topicDao: TopicDao,
    private val network: EleNetworkDataSource,
) : TopicsRepository {

    override fun getTopics(): Flow<List<Topic>> =
        topicDao.getTopicEntities()
            .map { it.map(TopicEntity::asExternalModel) }

    override fun getTopic(id: String): Flow<Topic> =
        topicDao.getTopicEntity(id).map { it.asExternalModel() }

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        synchronizer.changeListSync(
            versionReader = ChangeListVersions::topicVersion,
            changeListFetcher = { currentVersion ->
                network.getTopicChangeList(after = currentVersion)
            },
            versionUpdater = { latestVersion ->
                copy(topicVersion = latestVersion)
            },
            modelDeleter = topicDao::deleteTopics,
            modelUpdater = { changedIds ->
                val networkTopics = network.getTopics(ids = changedIds)
                topicDao.upsertTopics(
                    entities = networkTopics.map(NetworkTopic::asEntity),
                )
            },
        )
}
