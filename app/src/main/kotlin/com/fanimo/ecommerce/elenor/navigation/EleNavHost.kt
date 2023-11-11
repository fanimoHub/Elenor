package com.fanimo.ecommerce.elenor.navigation



import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.fanimo.ecommerce.elenor.feature.home.navigation.homeRoute
import com.fanimo.ecommerce.elenor.feature.home.navigation.homeScreen
import com.fanimo.ecommerce.elenor.feature.home.navigation.navigateToHome
import com.fanimo.ecommerce.elenor.feature.product.navigation.navigateToProduct
import com.fanimo.ecommerce.elenor.feature.product.navigation.productScreen
import com.fanimo.ecommerce.elenor.ui.EleAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun EleNavHost(
    appState: EleAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {

    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onProductClick = navController::navigateToProduct)
        productScreen(
            onHomeClick = navController::navigateToHome,
        )

    }
}
