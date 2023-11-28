
package com.fanimo.ecommerce.core.model.data

import kotlinx.datetime.Instant

data class UserNewsResource internal constructor(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String?,
    val publishDate: Instant,
    val type: String,
    val followableTopics: List<FollowableTopic>,
    val isSaved: Boolean,
    val hasBeenViewed: Boolean,
) {
    constructor(newsResource: NewsResource, userData: UserData) : this(
        id = newsResource.id,
        title = newsResource.title,
        content = newsResource.content,
        url = newsResource.url,
        headerImageUrl = newsResource.headerImageUrl,
        publishDate = newsResource.publishDate,
        type = newsResource.type,
        followableTopics = newsResource.topics.map { topic ->
            FollowableTopic(
                topic = topic,
                isFollowed = userData.followedTopics.contains(topic.id),
            )
        },
        isSaved = userData.bookmarkedNewsResources.contains(newsResource.id),
        hasBeenViewed = userData.viewedNewsResources.contains(newsResource.id),
    )
}

fun List<NewsResource>.mapToUserNewsResources(userData: UserData): List<UserNewsResource> {
    return map { UserNewsResource(it, userData) }
}
