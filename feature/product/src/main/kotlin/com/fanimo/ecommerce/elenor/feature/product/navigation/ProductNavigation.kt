package com.fanimo.ecommerce.elenor.feature.product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.product.ProductRoute

const val productRoute = "product_route"

fun NavController.navigateToProduct(navOptions: NavOptions? = null) {
    this.navigate(productRoute, navOptions)
}

fun NavGraphBuilder.productScreen(
    onHomeClick: () -> Unit,
) {
    composable(route = productRoute) {
        ProductRoute(onHomeClick, )
    }
}