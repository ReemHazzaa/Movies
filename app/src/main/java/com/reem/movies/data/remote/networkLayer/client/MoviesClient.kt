package com.reem.movies.data.remote.networkLayer.client


import com.example.moviez.data.remote.networkLayer.client.NetworkClient
import com.reem.movies.BuildConfig
import retrofit2.Retrofit

class MoviesClient(retrofitBuilder: Retrofit.Builder) : NetworkClient(retrofitBuilder) {

    override val baseUrl = BuildConfig.MOVIEZ_BASE_URL
}