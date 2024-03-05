package com.fanimo.ecommerce.elenor.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fanimo.ecommerce.designsystem.component.EleBackground
import com.fanimo.ecommerce.designsystem.component.EleGradientBackground
import com.fanimo.ecommerce.designsystem.component.EleTopAppBar
import com.fanimo.ecommerce.designsystem.icon.EleIcons
import com.fanimo.ecommerce.designsystem.theme.GradientColors
import com.fanimo.ecommerce.designsystem.theme.LocalGradientColors
import com.fanimo.ecommerce.elenor.R
import com.fanimo.ecommerce.elenor.feature.settings.SettingsDialog
import com.fanimo.ecommerce.elenor.navigation.EleNavHost
import com.fanimo.ecommerce.elenor.navigation.TopLevelDestination
import com.fanimo.ecommerce.elenor.feature.settings.R as settingsR


@OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EleApp(appState: EleAppState) {
    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == TopLevelDestination.HOME
    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    EleBackground {
        EleGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            // If user is not connected to the internet show a snack bar to inform them.
            val notConnectedMessage = stringResource(R.string.not_connected)
            LaunchedEffect(isOffline) {
                if (isOffline) {
                    snackbarHostState.showSnackbar(
                        message = notConnectedMessage,
                        duration = SnackbarDuration.Indefinite,
                    )
                }
            }

            if (showSettingsDialog) {
                SettingsDialog(
                    onDismiss = { showSettingsDialog = false },
                )
            }


            //val currentDestination = appState.currentDestination

            NavigationSuiteScaffold(
                layoutType = appState.navigationSuiteType,
                containerColor = Color.Transparent,
                navigationSuiteColors = NavigationSuiteDefaults.colors(
                    navigationRailContainerColor = Color.Transparent,
                    navigationDrawerContainerColor = Color.Transparent,
                ),
                navigationSuiteItems = {
                    appState.topLevelDestinations.forEach { destination ->
                        val isSelected = true

                        val isUnread = true
                        item(
                            selected = isSelected,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (isUnread) {
                                            Badge()
                                        }
                                    },
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) {
                                            destination.selectedIcon
                                        } else {
                                            destination.unselectedIcon
                                        },
                                        contentDescription = null,
                                    )
                                }
                            },
                            label = { Text(stringResource(destination.iconTextId)) },
                            onClick = { appState.navigateToTopLevelDestination(destination) },
                        )
                    }
                },
            ) {
                Scaffold(
                    topBar = {
                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
                            EleTopAppBar(
                                titleRes = destination.titleTextId,
                                navigationIcon = EleIcons.Search,
                                navigationIconContentDescription = stringResource(
                                    id = settingsR.string.top_app_bar_navigation_icon_description,
                                ),
                                actionIcon = EleIcons.Settings,
                                actionIconContentDescription = stringResource(
                                    id = settingsR.string.top_app_bar_action_icon_description,
                                ),
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color.Transparent,
                                ),
                                onActionClick = { showSettingsDialog = true },
                                onNavigationClick = { appState.navigateToSearch() },
                            )
                        }
                    },
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                ) { padding ->
                    EleNavHost(
                        appState = appState,
                        modifier = Modifier.padding(padding),
                    )
                }
            }
        }
    }



}