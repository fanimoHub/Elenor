

package com.fanimo.ecommerce.core.data.model

import com.fanimo.ecommerce.core.database.model.TopicEntity
import com.fanimo.ecommerce.core.network.model.NetworkTopic

fun NetworkTopic.asEntity() = TopicEntity(
    id = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
    url = url,
    imageUrl = imageUrl,
)
