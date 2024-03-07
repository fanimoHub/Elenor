
package com.fanimo.ecommerce.elenor.ui


import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.fanimo.ecommerce.core.data.repository.UserNewsResourceRepository

import com.fanimo.ecommerce.core.data.util.NetworkMonitor
import com.fanimo.ecommerce.core.data.util.TimeZoneMonitor
import com.fanimo.ecommerce.elenor.feature.account.navigation.accountRoute
import com.fanimo.ecommerce.elenor.feature.account.navigation.navigateToAccount
import com.fanimo.ecommerce.elenor.feature.cart.navigation.cartRoute
import com.fanimo.ecommerce.elenor.feature.cart.navigation.navigateToCart
import com.fanimo.ecommerce.elenor.feature.category.navigation.categoryRoute
import com.fanimo.ecommerce.elenor.feature.category.navigation.navigateToCategory
import com.fanimo.ecommerce.elenor.feature.home.navigation.homeRoute
import com.fanimo.ecommerce.elenor.feature.home.navigation.navigateToHome
import com.fanimo.ecommerce.elenor.feature.search.navigation.navigateToSearch
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.ACCOUNT
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.CART
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.CATEGORY
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.HOME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone



@Composable
fun rememberEleAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    userNewsResourceRepository: UserNewsResourceRepository,
    timeZoneMonitor: TimeZoneMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): EleAppState {
    //NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
        networkMonitor,
        userNewsResourceRepository,
        timeZoneMonitor,

        ) {
        EleAppState(
            navController = navController,
            coroutineScope = coroutineScope,
            windowSizeClass = windowSizeClass,
            networkMonitor = networkMonitor,
            userNewsResourceRepository = userNewsResourceRepository,
            timeZoneMonitor = timeZoneMonitor,
        )
    }
}

@Stable
class EleAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    userNewsResourceRepository: UserNewsResourceRepository,
    timeZoneMonitor: TimeZoneMonitor,

    ) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeRoute -> HOME
            accountRoute -> ACCOUNT
            categoryRoute -> CATEGORY
            cartRoute -> CART
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * The top level destinations that have unread news resources.
     */
    val topLevelDestinationsWithUnreadResources: StateFlow<Set<TopLevelDestination>> =
        userNewsResourceRepository.observeAllForFollowedTopics()
            .combine(userNewsResourceRepository.observeAllBookmarked()) { forYouNewsResources, bookmarkedNewsResources ->
                setOfNotNull(
                    ACCOUNT.takeIf { forYouNewsResources.any { !it.hasBeenViewed } },
                    CART.takeIf { bookmarkedNewsResources.any { !it.hasBeenViewed } },
                )
            }
            .stateIn(
                coroutineScope,
                SharingStarted.WhileSubscribed(5_000),
                initialValue = emptySet(),
            )

    val currentTimeZone = timeZoneMonitor.currentTimeZone
        .stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5_000),
            TimeZone.currentSystemDefault(),
        )


//    @OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
//    val navigationSuiteType: NavigationSuiteType
//        @Composable get() {
//            return if (windowSizeClass.widthSizeClass > WindowWidthSizeClass.Expanded
//            ) {
//                NavigationSuiteType.NavigationDrawer
//            } else if (windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium) {
//                NavigationSuiteType.NavigationRail
//            } else {
//                NavigationSuiteType.NavigationBar
//            }
//        }




    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                HOME -> navController.navigateToHome(topLevelNavOptions)
                CATEGORY -> navController.navigateToCategory(topLevelNavOptions)
                CART -> navController.navigateToCart(topLevelNavOptions)
                ACCOUNT -> navController.navigateToAccount(topLevelNavOptions)

            }
        }
    }

    fun navigateToSearch() = navController.navigateToSearch()

}

/**
 * Stores information about navigation events to be used with JankStats
 */
