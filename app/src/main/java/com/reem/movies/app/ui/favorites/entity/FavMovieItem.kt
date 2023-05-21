package com.reem.movies.app.ui.favorites.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.reem.movies.app.base.Constants.TABLE_MOVIES_FAV

@Entity(tableName = TABLE_MOVIES_FAV)
data class FavMovieItem(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var title: String,
    var imageUrl: String,
    var voteAvg: String,
)
