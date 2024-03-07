package com.fanimo.ecommerce.core.data.di

import com.fanimo.ecommerce.core.data.repository.DefaultRecentSearchRepository
import com.fanimo.ecommerce.core.data.repository.DefaultSearchContentsRepository
import com.fanimo.ecommerce.core.data.repository.NewsRepository
import com.fanimo.ecommerce.core.data.repository.OfflineFirstNewsRepository
import com.fanimo.ecommerce.core.data.repository.OfflineFirstTopicsRepository
import com.fanimo.ecommerce.core.data.repository.OfflineFirstUserDataRepository
import com.fanimo.ecommerce.core.data.repository.RecentSearchRepository
import com.fanimo.ecommerce.core.data.repository.SearchContentsRepository
import com.fanimo.ecommerce.core.data.repository.TopicsRepository
import com.fanimo.ecommerce.core.data.repository.UserDataRepository
import com.fanimo.ecommerce.core.data.util.ConnectivityManagerNetworkMonitor
import com.fanimo.ecommerce.core.data.util.NetworkMonitor
import com.fanimo.ecommerce.core.data.util.TimeZoneBroadcastMonitor
import com.fanimo.ecommerce.core.data.util.TimeZoneMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsTopicRepository(
        topicsRepository: OfflineFirstTopicsRepository,
    ): TopicsRepository

    @Binds
    internal abstract fun bindsNewsResourceRepository(
        newsRepository: OfflineFirstNewsRepository,
    ): NewsRepository

    @Binds
    internal abstract fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository

    @Binds
    internal abstract fun bindsRecentSearchRepository(
        recentSearchRepository: DefaultRecentSearchRepository,
    ): RecentSearchRepository

    @Binds
    internal abstract fun bindsSearchContentsRepository(
        searchContentsRepository: DefaultSearchContentsRepository,
    ): SearchContentsRepository

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
    @Binds
    internal abstract fun binds(impl: TimeZoneBroadcastMonitor): TimeZoneMonitor
}