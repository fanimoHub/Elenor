
package com.fanimo.ecommerce.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.fanimo.ecommerce.core.database.model.TopicEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [TopicEntity] access
 */
@Dao
interface TopicDao {
    @Query(
        value = """
        SELECT * FROM topics
        WHERE id = :topicId
    """,
    )
    fun getTopicEntity(topicId: String): Flow<TopicEntity>

    @Query(value = "SELECT * FROM topics")
    fun getTopicEntities(): Flow<List<TopicEntity>>

    @Query(value = "SELECT * FROM topics")
    suspend fun getOneOffTopicEntities(): List<TopicEntity>

    @Query(
        value = """
        SELECT * FROM topics
        WHERE id IN (:ids)
    """,
    )
    fun getTopicEntities(ids: Set<String>): Flow<List<TopicEntity>>

    /**
     * Inserts [topicEntities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTopics(topicEntities: List<TopicEntity>): List<Long>

    /**
     * Inserts or updates [entities] in the db under the specified primary keys
     */
    @Upsert
    suspend fun upsertTopics(entities: List<TopicEntity>)

    /**
     * Deletes rows in the db matching the specified [ids]
     */
    @Query(
        value = """
            DELETE FROM topics
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteTopics(ids: List<String>)
}
