

package com.fanimo.ecommerce.core.ui

import android.net.Uri
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.fanimo.ecommerce.core.analytics.LocalAnalyticsHelper
import com.fanimo.ecommerce.core.model.data.UserNewsResource

/**
 * Extension function for displaying a [List] of [NewsResourceCardExpanded] backed by a list of
 * [UserNewsResource]s.
 *
 * [onToggleBookmark] defines the action invoked when a user wishes to bookmark an item
 * When a news resource card is tapped it will open the news resource URL in a Chrome Custom Tab.
 */
fun LazyListScope.userNewsResourceCardItems(
    items: List<UserNewsResource>,
    onToggleBookmark: (item: UserNewsResource) -> Unit,
    onNewsResourceViewed: (String) -> Unit,
    onTopicClick: (String) -> Unit,
    itemModifier: Modifier = Modifier,
) = items(
    items = items,
    key = { it.id },
    itemContent = { userNewsResource ->
        val resourceUrl = Uri.parse(userNewsResource.url)
        val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
        val context = LocalContext.current
        val analyticsHelper = LocalAnalyticsHelper.current

        NewsResourceCardExpanded(
            userNewsResource = userNewsResource,
            isBookmarked = userNewsResource.isSaved,
            hasBeenViewed = userNewsResource.hasBeenViewed,
            onToggleBookmark = { onToggleBookmark(userNewsResource) },
            onClick = {
                analyticsHelper.logNewsResourceOpened(
                    newsResourceId = userNewsResource.id,
                )
                launchCustomChromeTab(context, resourceUrl, backgroundColor)
                onNewsResourceViewed(userNewsResource.id)
            },
            onTopicClick = onTopicClick,
            modifier = itemModifier,
        )
    },
)
