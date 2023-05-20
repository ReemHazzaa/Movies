package com.reem.movies.app.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reem.movies.app.base.Constants.TABLE_MOVIES_CACHED

@Entity(tableName = TABLE_MOVIES_CACHED)
data class MovieUiItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
)
