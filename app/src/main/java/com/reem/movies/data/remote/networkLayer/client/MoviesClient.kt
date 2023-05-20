package com.reem.movies.data.remote.networkLayer.client


import com.reem.movies.BuildConfig
import retrofit2.Retrofit

class MoviesClient(retrofitBuilder: Retrofit.Builder) : NetworkClient(retrofitBuilder) {

    override val baseUrl = BuildConfig.MOVIEZ_BASE_URL
}