
package com.fanimo.ecommerce.core.network

import com.fanimo.ecommerce.core.network.model.NetworkChangeList
import com.fanimo.ecommerce.core.network.model.NetworkNewsResource
import com.fanimo.ecommerce.core.network.model.NetworkTopic

/**
 * Interface representing network calls to the Elenor backend
 */
interface EleNetworkDataSource {
    suspend fun getTopics(ids: List<String>? = null): List<NetworkTopic>

    suspend fun getNewsResources(ids: List<String>? = null): List<NetworkNewsResource>

    suspend fun getTopicChangeList(after: Int? = null): List<NetworkChangeList>

    suspend fun getNewsResourceChangeList(after: Int? = null): List<NetworkChangeList>
}
