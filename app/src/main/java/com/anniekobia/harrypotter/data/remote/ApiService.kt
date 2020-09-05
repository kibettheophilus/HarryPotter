package com.anniekobia.harrypotter.data.remote

import com.anniekobia.harrypotter.data.remote.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //Fetch all characters in the series
    @GET(".")
    suspend fun getAllCharacters(): Response<CharacterList>

    //Fetch characters who were students in the series
    @GET("students")
    suspend fun getStudentCharacters(): Response<CharacterList>

    //Fetch characters who were staff in the series
    @GET("staff")
    suspend fun getStaffCharacters(): Response<CharacterList>

    //Fetch characters living in the specific house in the series
    @GET("house/:")
    suspend fun getCharactersInHouse(
            @Query("house") house: String
    ): Response<CharacterList>
}