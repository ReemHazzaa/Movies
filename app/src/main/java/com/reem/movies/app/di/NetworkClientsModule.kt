package com.example.moviez.app.di

import com.example.moviez.data.remote.networkLayer.client.MoviezClient
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
    fun provideMoviezNetworkClient(retrofitBuilder: Retrofit.Builder): MoviezClient {
        return MoviezClient(retrofitBuilder)
    }

}