
package com.fanimo.ecommerce.core.database

import com.fanimo.ecommerce.core.database.dao.NewsResourceDao
import com.fanimo.ecommerce.core.database.dao.NewsResourceFtsDao
import com.fanimo.ecommerce.core.database.dao.RecentSearchQueryDao
import com.fanimo.ecommerce.core.database.dao.TopicDao
import com.fanimo.ecommerce.core.database.dao.TopicFtsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: EleDatabase,
    ): TopicDao = database.topicDao()

    @Provides
    fun providesNewsResourceDao(
        database: EleDatabase,
    ): NewsResourceDao = database.newsResourceDao()

    @Provides
    fun providesTopicFtsDao(
        database: EleDatabase,
    ): TopicFtsDao = database.topicFtsDao()

    @Provides
    fun providesNewsResourceFtsDao(
        database: EleDatabase,
    ): NewsResourceFtsDao = database.newsResourceFtsDao()

    @Provides
    fun providesRecentSearchQueryDao(
        database: EleDatabase,
    ): RecentSearchQueryDao = database.recentSearchQueryDao()
}
