
package com.fanimo.ecommerce.elenore.sync.di

import com.fanimo.ecommerce.core.data.util.SyncManager
import com.fanimo.ecommerce.elenor.sync.status.StubSyncSubscriber
import com.fanimo.ecommerce.elenor.sync.status.SyncSubscriber
import com.fanimo.ecommerce.elenor.sync.status.WorkManagerSyncManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SyncModule {
    @Binds
    fun bindsSyncStatusMonitor(
        syncStatusMonitor: WorkManagerSyncManager,
    ): SyncManager

    @Binds
    fun bindsSyncSubscriber(
        syncSubscriber: StubSyncSubscriber,
    ): SyncSubscriber
}
