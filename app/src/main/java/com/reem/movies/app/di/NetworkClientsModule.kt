package com.reem.movies.app.di

import com.reem.movies.data.remote.networkLayer.client.MoviesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientsModule {

    @Provides
    @Singleton
    fun provideMoviezNetworkClient(retrofitBuilder: Retrofit.Builder): MoviesClient {
        return MoviesClient(retrofitBuilder)
    }

}