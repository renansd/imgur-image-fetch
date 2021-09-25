package com.example.network

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitialization {

    private var baseUrl: String = "https://api.imgur.com/3/"

    fun retrofitAdapterProvider(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return addInterceptors(OkHttpClient.Builder()).authenticator(buildAuthenticator()).build()
    }

    private fun addInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.addInterceptor(buildDebugInterceptor())

        return builder
    }

    private fun buildAuthenticator(): Authenticator {
        return Authenticator { _, response ->
            response.request().newBuilder().header("Authorization", "Client-ID 1ceddedc03a5d71").build()
        }
    }

    private fun buildDebugInterceptor(): HttpLoggingInterceptor {
        val debugInterceptor = HttpLoggingInterceptor()
        debugInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return debugInterceptor
    }
}