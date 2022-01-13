package com.anniekobia.harrypotter.data.remote

import com.anniekobia.harrypotter.data.remote.model.CharacterList
import com.anniekobia.harrypotter.data.remote.model.CharacterTwoList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    /**
     * Fetch all characters in the series
     * This characters will be stored in the local sqlite database and retrieved to populate the various screens
     */
    @GET(".")
    suspend fun getAllCharacters(): Response<CharacterTwoList>
}