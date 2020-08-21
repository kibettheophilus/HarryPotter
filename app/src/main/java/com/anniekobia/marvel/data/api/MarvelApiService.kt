package com.anniekobia.marvel.data.api

import com.anniekobia.marvel.data.api.model.marvelapi.Marvelhero
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("characters")
    fun getMarvelheroes(
        @Query("orderBy") orderBy: String?,
        @Query("limit") limit: Int?,
        @Query("ts") timestamp: String?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?
    ): retrofit2.Call<Marvelhero?>?

}