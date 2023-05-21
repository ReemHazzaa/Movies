package com.reem.movies.data.local.movieCache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.reem.movies.app.base.Constants.TABLE_MOVIES_CACHED
import com.reem.movies.app.ui.home.entity.movie.MovieItem

@Dao
interface MovieCacheDao {
    @Query("SELECT * FROM $TABLE_MOVIES_CACHED")
    fun getAllCachedMovies(): LiveData<List<MovieItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieItem(item: MovieItem)

    @Delete
    suspend fun deleteMovieItem(item: MovieItem)

    @Query("DELETE FROM $TABLE_MOVIES_CACHED")
    suspend fun deleteCache()
}