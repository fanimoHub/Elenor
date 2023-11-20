
package com.fanimo.ecommerce.elenor.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.fanimo.ecommerce.elenor.R
import com.fanimo.ecommerce.designsystem.icon.EleIcons
import com.fanimo.ecommerce.elenor.feature.home.R as homeR
import com.fanimo.ecommerce.elenor.feature.product.R as productR
import com.fanimo.ecommerce.elenor.feature.account.R as accountR

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
        titleTextId = homeR.string.home,
    ),

    ACCOUNT(
        selectedIcon = EleIcons.Account,
        unselectedIcon = EleIcons.AccountBorder,
        iconTextId = accountR.string.account,
        titleTextId = accountR.string.account,
    ),

    PRODUCT(
        selectedIcon = EleIcons.Upcoming,
        unselectedIcon = EleIcons.UpcomingBorder,
        iconTextId = productR.string.product,
        titleTextId = R.string.app_name,
    ),
}
