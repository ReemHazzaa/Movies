package com.reem.movies.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.app.entity.movie.MovieUiItem
import com.reem.movies.data.local.fav.FavDao
import com.reem.movies.data.local.movieCache.MovieCacheDao

@Database(entities = [MovieUiItem::class, FavMovieItem::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun cacheDao(): MovieCacheDao
    abstract fun favDao(): FavDao
}