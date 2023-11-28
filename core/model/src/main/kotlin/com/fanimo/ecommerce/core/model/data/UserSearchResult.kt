
package com.fanimo.ecommerce.core.model.data


data class UserSearchResult(
    val topics: List<FollowableTopic> = emptyList(),
    val newsResources: List<UserNewsResource> = emptyList(),
)
