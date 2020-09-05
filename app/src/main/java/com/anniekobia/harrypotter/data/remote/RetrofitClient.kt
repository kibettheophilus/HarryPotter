package com.anniekobia.harrypotter.data.remote

import com.anniekobia.harrypotter.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Review : This class can be include in the api package and this package could use
    // abit of refactoring
    // Review : make class variables be private

    private const val BASE_URL = "http://hp-api.herokuapp.com/api/characters/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()

    val apiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(
            ApiService::
            class.java
        )
}
