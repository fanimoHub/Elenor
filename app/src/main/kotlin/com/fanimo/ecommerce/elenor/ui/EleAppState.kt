package com.fanimo.ecommerce.elenor.ui



import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberEleAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): EleAppState {
    //NavigationTrackingSideEffect(navController)
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,

    ) {
        EleAppState(
            navController,
            coroutineScope,
            windowSizeClass,
        )
    }
}

@Stable
class EleAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
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

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar




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


}

