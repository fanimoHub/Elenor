package com.fanimo.ecommerce.core.data.repository

import com.fanimo.ecommerce.core.model.data.DarkThemeConfig
import com.fanimo.ecommerce.core.model.data.ThemeBrand
import com.fanimo.ecommerce.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun setFollowedTopicIds(followedTopicIds: Set<String>)


    suspend fun setTopicIdFollowed(followedTopicId: String, followed: Boolean)


    suspend fun updateNewsResourceBookmark(newsResourceId: String, bookmarked: Boolean)


    suspend fun setNewsResourceViewed(newsResourceId: String, viewed: Boolean)


    suspend fun setThemeBrand(themeBrand: ThemeBrand)


    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)


    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)


    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean)
}