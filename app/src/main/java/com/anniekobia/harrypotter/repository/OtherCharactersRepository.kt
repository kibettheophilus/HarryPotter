package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.utils.NetworkResult
import com.anniekobia.harrypotter.utils.safeApiCall
import java.io.IOException


class OtherCharactersRepository(context: Context) {
    // Review : Consider adding a DI Framework
    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private val allCharactersRepository = AllCharactersRepository(context)

    /**
     * Repository method to get all characters who are neither students nor staff from the local sqlite db
     * if no data in room, fetch all characters and tell user to refresh
     */
    suspend fun getOtherCharacters() = safeApiCall(
        call = { getCharacters() },
        errorMessage = "Something went wrong. Please tap the icon to refresh"
    )

    private suspend fun getCharacters(): NetworkResult<List<Character>> {
        val response = characterDAO!!.getOtherCharacters()
        return when {
            response.isEmpty() -> {
                Log.e("Local:Other", "No characters in room")
                allCharactersRepository.getAllCharacters()
                NetworkResult.Error(IOException("Something went wrong. Please refresh"))
            }
            else -> {
                Log.e("Local:Other", "Other characters fetched")
                NetworkResult.Success(response)
            }
        }
    }
}