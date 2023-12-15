package com.fanimo.ecommerce.elenor.feature.product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.product.ProductRoute

const val productRoute = "product_route"

fun NavController.navigateToProduct(productId: String) {
    this.navigate(productRoute)
}

fun NavGraphBuilder.productScreen(
    onHomeClick: () -> Unit,
) {
    composable(route = productRoute) {
        ProductRoute(onHomeClick, )
    }
}