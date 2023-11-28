
package com.fanimo.ecommerce.core.model.data

/**
 * A [topic] with the additional information for whether or not it is followed.
 */
data class FollowableTopic( // TODO consider changing to UserTopic and flattening
    val topic: Topic,
    val isFollowed: Boolean,
)
