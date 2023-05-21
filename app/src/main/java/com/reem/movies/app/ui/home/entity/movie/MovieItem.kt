package com.reem.movies.app.ui.home.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reem.movies.app.base.Constants.TABLE_MOVIES_CACHED

@Entity(tableName = TABLE_MOVIES_CACHED)
data class MovieItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
)
