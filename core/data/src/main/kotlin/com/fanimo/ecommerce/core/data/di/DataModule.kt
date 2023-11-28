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
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsTopicRepository(
        topicsRepository: OfflineFirstTopicsRepository,
    ): TopicsRepository

    @Binds
    fun bindsNewsResourceRepository(
        newsRepository: OfflineFirstNewsRepository,
    ): NewsRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository

    @Binds
    fun bindsRecentSearchRepository(
        recentSearchRepository: DefaultRecentSearchRepository,
    ): RecentSearchRepository

    @Binds
    fun bindsSearchContentsRepository(
        searchContentsRepository: DefaultSearchContentsRepository,
    ): SearchContentsRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}