package com.fanimo.ecommerce.elenor.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.home.HomeRoute

const val LINKED_NEWS_RESOURCE_ID = "linkedNewsResourceId"
const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onProductClick: (productId: String) -> Unit,
) {
    composable(route = homeRoute) {
        HomeRoute( onProductClick )
    }
}