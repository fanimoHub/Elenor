
package com.fanimo.ecommerce.elenor.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.fanimo.ecommerce.elenor.R
import com.fanimo.ecommerce.designsystem.icon.EleIcons
import com.fanimo.ecommerce.elenor.feature.home.R as homeR
import com.fanimo.ecommerce.elenor.feature.category.R as categoryR
import com.fanimo.ecommerce.elenor.feature.account.R as accountR
import com.fanimo.ecommerce.elenor.feature.cart.R as cartR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {

    HOME(
        selectedIcon = EleIcons.Home,
        unselectedIcon = EleIcons.HomeBorder,
        iconTextId = homeR.string.home,
        titleTextId = R.string.app_name,
    ),

    CATEGORY(
        selectedIcon = EleIcons.Upcoming,
        unselectedIcon = EleIcons.UpcomingBorder,
        iconTextId = categoryR.string.category,
        titleTextId = categoryR.string.category,
    ),

    CART(
        selectedIcon = EleIcons.Cart,
        unselectedIcon = EleIcons.CartBorder,
        iconTextId = cartR.string.cart,
        titleTextId = cartR.string.cart,
    ),

    ACCOUNT(
        selectedIcon = EleIcons.Account,
        unselectedIcon = EleIcons.AccountBorder,
        iconTextId = accountR.string.account_icon_text,
        titleTextId = accountR.string.account_page_name,
    ),
}
