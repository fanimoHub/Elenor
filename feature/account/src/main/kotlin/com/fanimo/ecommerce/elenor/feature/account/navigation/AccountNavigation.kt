package com.fanimo.ecommerce.elenor.feature.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.fanimo.ecommerce.elenor.feature.account.AccountRoute
import com.fanimo.ecommerce.elenor.feature.account.auth.AuthRoute

const val accountRoute = "account_route"
const val authRoute = "auth_route"

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

fun NavController.navigateToAuth(navOptions: NavOptions? = null) {
    this.navigate(authRoute, navOptions)
}

fun NavGraphBuilder.authScreen(
    onHomeClick: () -> Unit,
    popBack: () -> Boolean,
) {
    composable(route = authRoute) {
        AuthRoute(
            onHomeClick,
            popBack,
        )
    }
}