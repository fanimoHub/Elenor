
package com.fanimo.ecommerce.core.data.repository

import com.fanimo.ecommerce.core.model.data.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * Data layer interface for the search feature.
 */
interface SearchContentsRepository {

    /**
     * Populate the fts tables for the search contents.
     */
    suspend fun populateFtsData()

    /**
     * Query the contents matched with the [searchQuery] and returns it as a [Flow] of [SearchResult]
     */
    fun searchContents(searchQuery: String): Flow<SearchResult>

    fun getSearchContentsCount(): Flow<Int>
}
