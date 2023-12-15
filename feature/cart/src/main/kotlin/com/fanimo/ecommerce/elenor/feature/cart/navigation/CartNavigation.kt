package com.fanimo.ecommerce.elenor.feature.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.cart.CartRoute

const val cartRoute = "cart_route"

fun NavController.navigateToCart(navOptions: NavOptions? = null) {
    this.navigate(cartRoute, navOptions)
}

fun NavGraphBuilder.cartScreen(
    onHomeClick: () -> Unit,
) {
    composable(route = cartRoute) {
        CartRoute(onHomeClick)
    }
}