package com.anniekobia.marvel.data.retrofit

import com.anniekobia.marvel.data.api.MarvelApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarvelApiClient {
    private val BASE_URL_MARVELAPI = "https://gateway.marvel.com:443/v1/public/"
    private var marvelApiService: MarvelApiService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        marvelApiService = Retrofit.Builder()
                .baseUrl(BASE_URL_MARVELAPI)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelApiService::class.java)
    }
}