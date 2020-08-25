package com.anniekobia.marvel.data.api

import com.anniekobia.marvel.data.api.model.superheroapi.Superhero
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {
    @GET("{access_token}/search/{name}")
    fun getSuperheroDetails(
            @Path("access_token") accessToken: Long,
            @Path("name") name: String
    ): retrofit2.Call<Superhero>
}