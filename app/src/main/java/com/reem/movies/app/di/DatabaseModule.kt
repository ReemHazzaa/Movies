package com.reem.movies.app.di

import android.content.Context
import androidx.room.Room
import com.reem.movies.app.base.Constants.MOVIES_DB_NAME
import com.reem.movies.data.local.database.MoviesDatabase
import com.reem.movies.data.local.fav.FavDao
import com.reem.movies.data.local.movieCache.MovieCacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MOVIES_DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideMoviesCacheDao(database: MoviesDatabase): MovieCacheDao = database.cacheDao()

    @Singleton
    @Provides
    fun provideFavDao(database: MoviesDatabase): FavDao = database.favDao()
}