package com.fanimo.ecommerce.elenor.feature.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.account.AccountRoute

const val accountRoute = "account_route"

fun NavController.navigateToAccount(navOptions: NavOptions? = null) {
    this.navigate(accountRoute, navOptions)
}

fun NavGraphBuilder.accountScreen(
    onHomeClick: () -> Unit,
) {
    composable(route = accountRoute) {
        AccountRoute(onHomeClick)
    }
}