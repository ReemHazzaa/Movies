package com.reem.movies.data.local.fav

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reem.movies.app.base.Constants.TABLE_MOVIES_FAV
import com.reem.movies.app.entity.favMovie.FavMovieItem

@Dao
interface FavDao {
    @Query("SELECT * FROM $TABLE_MOVIES_FAV")
    fun getAllFav(): LiveData<List<FavMovieItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavItem(item: FavMovieItem)

    @Delete
    suspend fun deleteFavItem(item: FavMovieItem)
}