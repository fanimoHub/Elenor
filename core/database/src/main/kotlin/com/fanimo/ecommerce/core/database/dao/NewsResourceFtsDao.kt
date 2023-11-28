

package com.fanimo.ecommerce.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fanimo.ecommerce.core.database.model.NewsResourceFtsEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [NewsResourceFtsEntity] access.
 */
@Dao
interface NewsResourceFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsResources: List<NewsResourceFtsEntity>)

    @Query("SELECT newsResourceId FROM newsResourcesFts WHERE newsResourcesFts MATCH :query")
    fun searchAllNewsResources(query: String): Flow<List<String>>

    @Query("SELECT count(*) FROM newsResourcesFts")
    fun getCount(): Flow<Int>
}
