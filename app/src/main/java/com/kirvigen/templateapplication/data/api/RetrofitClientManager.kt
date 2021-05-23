package com.kirvigen.templateapplication.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientManager {

    private fun loggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun getClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor())
        .build()

    fun getApi(): TwitchApi {
        return Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/kraken/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getClient())
            .build()
            .create(TwitchApi::class.java)
    }

}