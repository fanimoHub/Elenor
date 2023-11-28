

package com.fanimo.ecommerce.elenor.feature.search

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.fanimo.ecommerce.core.model.data.FollowableTopic
import com.fanimo.ecommerce.core.ui.PreviewParameterData.newsResources
import com.fanimo.ecommerce.core.ui.PreviewParameterData.topics

/* ktlint-disable max-line-length */
/**
 * This [PreviewParameterProvider](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider)
 * provides list of [SearchResultUiState] for Composable previews.
 */
class SearchUiStatePreviewParameterProvider : PreviewParameterProvider<SearchResultUiState> {
    override val values: Sequence<SearchResultUiState> = sequenceOf(
        SearchResultUiState.Success(
            topics = topics.mapIndexed { i, topic ->
                FollowableTopic(topic = topic, isFollowed = i % 2 == 0)
            },
            newsResources = newsResources,
        ),
    )
}
