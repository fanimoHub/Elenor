package com.fanimo.ecommerce.elenor.navigation



import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.fanimo.ecommerce.elenor.feature.account.navigation.accountScreen
import com.fanimo.ecommerce.elenor.feature.cart.navigation.cartScreen
import com.fanimo.ecommerce.elenor.feature.category.navigation.categoryScreen
import com.fanimo.ecommerce.elenor.feature.category.navigation.navigateToCategory
import com.fanimo.ecommerce.elenor.feature.home.navigation.homeRoute
import com.fanimo.ecommerce.elenor.feature.home.navigation.homeScreen
import com.fanimo.ecommerce.elenor.feature.home.navigation.navigateToHome
import com.fanimo.ecommerce.elenor.feature.product.navigation.navigateToProduct
import com.fanimo.ecommerce.elenor.feature.product.navigation.productScreen
import com.fanimo.ecommerce.elenor.ui.EleAppState


@Composable
fun EleNavHost(
    appState: EleAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute,
) {

    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen(onProductClick = navController::navigateToProduct,
            onShowSnackbar = onShowSnackbar,
            )
        categoryScreen(onHomeClick = navController::navigateToHome)
        cartScreen(onHomeClick = navController::navigateToHome)
        accountScreen(onHomeClick = navController::navigateToHome)
        productScreen(onHomeClick = navController::navigateToHome )

    }
}
