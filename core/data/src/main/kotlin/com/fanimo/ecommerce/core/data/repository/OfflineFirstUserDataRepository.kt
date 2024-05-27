
package com.fanimo.ecommerce.core.data.repository

import androidx.annotation.VisibleForTesting
import com.fanimo.ecommerce.core.analytics.AnalyticsHelper
import com.fanimo.ecommerce.core.datastore.ElePreferencesDataSource
import com.fanimo.ecommerce.core.model.data.DarkThemeConfig
import com.fanimo.ecommerce.core.model.data.ThemeBrand
import com.fanimo.ecommerce.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val elePreferencesDataSource: ElePreferencesDataSource,
    private val analyticsHelper: AnalyticsHelper,
) : UserDataRepository {

    override val userData: Flow<UserData> =
        elePreferencesDataSource.userData

    @VisibleForTesting
    override suspend fun setFollowedTopicIds(followedTopicIds: Set<String>) =
        elePreferencesDataSource.setFollowedTopicIds(followedTopicIds)

    override suspend fun setTopicIdFollowed(followedTopicId: String, followed: Boolean) {
        elePreferencesDataSource.setTopicIdFollowed(followedTopicId, followed)
        analyticsHelper.logTopicFollowToggled(followedTopicId, followed)
    }

    override suspend fun updateNewsResourceBookmark(newsResourceId: String, bookmarked: Boolean) {
        elePreferencesDataSource.setNewsResourceBookmarked(newsResourceId, bookmarked)
        analyticsHelper.logNewsResourceBookmarkToggled(
            newsResourceId = newsResourceId,
            isBookmarked = bookmarked,
        )
    }

    override suspend fun setNewsResourceViewed(newsResourceId: String, viewed: Boolean) =
        elePreferencesDataSource.setNewsResourceViewed(newsResourceId, viewed)

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
        elePreferencesDataSource.setThemeBrand(themeBrand)
        analyticsHelper.logThemeChanged(themeBrand.name)
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        elePreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
        analyticsHelper.logDarkThemeConfigChanged(darkThemeConfig.name)
    }

    override suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        elePreferencesDataSource.setDynamicColorPreference(useDynamicColor)
        analyticsHelper.logDynamicColorPreferenceChanged(useDynamicColor)
    }

    override suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean) {
        elePreferencesDataSource.setShouldHideOnboarding(shouldHideOnboarding)
        analyticsHelper.logOnboardingStateChanged(shouldHideOnboarding)
    }

    override suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        elePreferencesDataSource.setIsLoggedIn(isLoggedIn)
    }
}
