
package com.fanimo.ecommerce.elenor.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.tracing.traceAsync
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.fanimo.ecommerce.core.analytics.AnalyticsHelper
import com.fanimo.ecommerce.core.data.Synchronizer
import com.fanimo.ecommerce.core.data.repository.NewsRepository
import com.fanimo.ecommerce.core.data.repository.SearchContentsRepository
import com.fanimo.ecommerce.core.data.repository.TopicsRepository
import com.fanimo.ecommerce.core.datastore.ChangeListVersions
import com.fanimo.ecommerce.core.datastore.ElePreferencesDataSource
import com.fanimo.ecommerce.core.network.Dispatcher
import com.fanimo.ecommerce.core.network.EleDispatchers.IO
import com.fanimo.ecommerce.elenor.sync.initializers.SyncConstraints
import com.fanimo.ecommerce.elenor.sync.initializers.syncForegroundInfo
import com.fanimo.ecommerce.elenor.sync.status.SyncSubscriber
import com.fanimo.ecommerce.elenor.sync.workers.DelegatingWorker
import com.fanimo.ecommerce.elenor.sync.workers.delegatedData
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

/**
 * Syncs the data layer by delegating to the appropriate repository instances with
 * sync functionality.
 */
@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val elePreferences: ElePreferencesDataSource,
    private val topicRepository: TopicsRepository,
    private val newsRepository: NewsRepository,
    private val searchContentsRepository: SearchContentsRepository,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val analyticsHelper: AnalyticsHelper,
    private val syncSubscriber: SyncSubscriber,
) : CoroutineWorker(appContext, workerParams), Synchronizer {

    override suspend fun getForegroundInfo(): ForegroundInfo =
        appContext.syncForegroundInfo()

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        traceAsync("Sync", 0) {
            analyticsHelper.logSyncStarted()

            syncSubscriber.subscribe()

            // First sync the repositories in parallel
            val syncedSuccessfully = awaitAll(
                async { topicRepository.sync() },
                async { newsRepository.sync() },
            ).all { it }

            analyticsHelper.logSyncFinished(syncedSuccessfully)

            if (syncedSuccessfully) {
                searchContentsRepository.populateFtsData()
                Result.success()
            } else {
                Result.retry()
            }
        }
    }

    override suspend fun getChangeListVersions(): ChangeListVersions =
        elePreferences.getChangeListVersions()

    override suspend fun updateChangeListVersions(
        update: ChangeListVersions.() -> ChangeListVersions,
    ) = elePreferences.updateChangeListVersion(update)

    companion object {
        /**
         * Expedited one time work to sync data on app startup
         */
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}
