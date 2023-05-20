package com.reem.movies.app.di

import com.reem.movies.data.remote.apiService.MoviesApiService
import com.reem.movies.data.remote.networkLayer.client.MoviesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServicesModule {

    @Provides
    @Singleton
    fun provideMoviesApiService(moviezClient: MoviesClient): MoviesApiService {
        return moviezClient.build().create(MoviesApiService::class.java)
    }

}