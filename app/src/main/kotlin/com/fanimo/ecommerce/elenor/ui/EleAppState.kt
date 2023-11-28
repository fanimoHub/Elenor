package com.fanimo.ecommerce.elenor.ui



import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.fanimo.ecommerce.elenor.feature.account.navigation.accountRoute
import com.fanimo.ecommerce.elenor.feature.account.navigation.navigateToAccount
import com.fanimo.ecommerce.elenor.feature.home.navigation.homeRoute
import com.fanimo.ecommerce.elenor.feature.home.navigation.navigateToHome
import com.fanimo.ecommerce.elenor.feature.product.navigation.navigateToProduct
import com.fanimo.ecommerce.elenor.feature.product.navigation.productRoute
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.ACCOUNT
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.HOME
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination.PRODUCT
import com.fanimo.ecommerce.elenor.feature.search.navigation.navigateToSearch
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import com.fanimo.ecommerce.core.data.util.NetworkMonitor


@Composable
fun rememberEleAppState(
    windowSize:DpSize,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    networkMonitor: NetworkMonitor,
): EleAppState {
    //NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
        windowSize,
        networkMonitor,

    ) {
        EleAppState(
            navController,
            coroutineScope,
            windowSize,
            networkMonitor,
        )
    }
}

@Stable
class EleAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    private val windowSize: DpSize,
    networkMonitor: NetworkMonitor,

    ) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeRoute -> HOME
            accountRoute -> ACCOUNT
            productRoute -> PRODUCT
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )



    @OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class)
    val navigationSuiteType: NavigationSuiteType
        @Composable get() {
            return if (windowSize.width > 1240.dp) {
                NavigationSuiteType.NavigationDrawer
            } else if (windowSize.width >= 600.dp) {
                NavigationSuiteType.NavigationRail
            } else {
                NavigationSuiteType.NavigationBar
            }
        }




    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()




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
                ACCOUNT -> navController.navigateToAccount(topLevelNavOptions)
                PRODUCT -> navController.navigateToProduct(topLevelNavOptions)
            }
        }
    }

    fun navigateToSearch() {
        navController.navigateToSearch()
    }


}

