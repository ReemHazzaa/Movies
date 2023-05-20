package com.reem.movies.data.remote.networkLayer.interceptor

import com.reem.movies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Interceptor is to add API key to all API requests.
 */
class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val ogRequest = chain.request()
        val ogHttpUrl = ogRequest.url

        val url = ogHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "en-US")
            .build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = ogRequest.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}