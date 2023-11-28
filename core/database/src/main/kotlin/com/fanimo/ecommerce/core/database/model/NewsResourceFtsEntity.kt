

package com.fanimo.ecommerce.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

/**
 * Fts entity for the news resources. See https://developer.android.com/reference/androidx/room/Fts4.
 */
@Entity(tableName = "newsResourcesFts")
@Fts4
data class NewsResourceFtsEntity(

    @ColumnInfo(name = "newsResourceId")
    val newsResourceId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,
)
