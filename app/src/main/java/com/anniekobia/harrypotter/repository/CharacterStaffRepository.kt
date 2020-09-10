package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.utils.NetworkResult
import java.io.IOException


class CharacterStaffRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private val allCharactersRepository = AllCharactersRepository(context)

    /**
     * Repository method to get all characters who are staff from the local sqlite db
     * if no data in room, fetch all characters and tell user to refresh
     */
    suspend fun getStaffCharacters(): NetworkResult<List<Character>> {
        val response = characterDAO!!.getStaffCharacters()
        return when {
            response.isEmpty() -> {
                Log.e("Local:Staff", "No characters in room")
                allCharactersRepository.getAllCharacters()
                NetworkResult.Error(IOException("Something went wrong. Please refresh"))
            }
            else -> {
                Log.e("Local:Staff", "Staff characters fetched")
                NetworkResult.Success(response)
            }
        }
    }
}