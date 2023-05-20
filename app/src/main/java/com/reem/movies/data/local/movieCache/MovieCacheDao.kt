package com.reem.movies.data.local.movieCache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reem.movies.app.base.Constants.TABLE_MOVIES_CACHED
import com.reem.movies.app.entity.movie.MovieUiItem

@Dao
interface MovieCacheDao {
    @Query("SELECT * FROM $TABLE_MOVIES_CACHED")
    fun getAllCachedMovies(): LiveData<List<MovieUiItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieItem(item: MovieUiItem)

    @Delete
    suspend fun deleteMovieItem(item: MovieUiItem)
}