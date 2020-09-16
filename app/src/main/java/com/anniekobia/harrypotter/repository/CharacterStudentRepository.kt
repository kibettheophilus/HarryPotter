package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.utils.NetworkResult
import com.anniekobia.harrypotter.utils.safeApiCall
import java.io.IOException

class CharacterStudentRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private val allCharactersRepository = AllCharactersRepository(context)

    /**
     * Repository method to get all characters who are students from the local sqlite db
     * if no data in room, fetch all characters and tell user to refresh
     */
    suspend fun getStudentCharacters() = safeApiCall(
        call = { getCharacters() },
        errorMessage = "Something went wrong. Please tap the icon to refresh"
    )

    private suspend fun getCharacters(): NetworkResult<List<Character>> {
        val response = characterDAO!!.getStudentCharacters()
        return when {
            response.isEmpty() -> {
                Log.e("Local:Students", "No characters in room")
                allCharactersRepository.getAllCharacters()
                NetworkResult.Error(IOException("Something went wrong. Please refresh"))
            }
            else -> {
                Log.e("Local:Students", "Student characters fetched")
                NetworkResult.Success(response)
            }
        }
    }

}