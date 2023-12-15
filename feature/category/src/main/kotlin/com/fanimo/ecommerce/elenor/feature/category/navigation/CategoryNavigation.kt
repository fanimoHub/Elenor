package com.fanimo.ecommerce.elenor.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.category.CategoryRoute

const val categoryRoute = "category_route"

fun NavController.navigateToCategory(navOptions: NavOptions? = null) {
    this.navigate(categoryRoute, navOptions)
}

fun NavGraphBuilder.categoryScreen(
    onHomeClick: () -> Unit,
) {
    composable(route = categoryRoute) {
        CategoryRoute(onHomeClick)
    }
}