package com.cubos.cine.networking

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {

    companion object {
        private const val API_KEY_QUERY = "api_key"
        private const val API_QUERY_VALUE = "b727fd6dc7a1a30f2ce43cc0fd56c15f"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val baseRequest = chain.request()

        val url = baseRequest.url()
            .newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_QUERY_VALUE)
            .build()

        val requestBuilder = baseRequest.newBuilder()
            .url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}