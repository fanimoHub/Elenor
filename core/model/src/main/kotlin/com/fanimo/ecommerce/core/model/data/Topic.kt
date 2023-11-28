
package com.fanimo.ecommerce.core.model.data

/**
 * External data layer representation of a Elenor Topic
 */
data class Topic(
    val id: String,
    val name: String,
    val shortDescription: String,
    val longDescription: String,
    val url: String,
    val imageUrl: String,
)
