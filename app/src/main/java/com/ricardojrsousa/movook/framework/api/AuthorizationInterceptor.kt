package com.ricardojrsousa.movook.framework.api

import com.ricardojrsousa.movook.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val API_KEY = "api_key"
private const val LANGUAGE = "language"

class AuthorizationInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val apiKey = "040509eb18929f6db970dba1c4f0f836"
        val language = "en-US"

        val currentRequest = chain.request()

        val url = currentRequest.url().newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .addQueryParameter(LANGUAGE, language)
            .build()

        val newRequest = currentRequest.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}