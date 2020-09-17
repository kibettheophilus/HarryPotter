package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.remote.RetrofitClient
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.data.remote.model.CharacterList
import com.anniekobia.harrypotter.utils.NetworkResult
import com.anniekobia.harrypotter.utils.safeApiCall
import java.io.IOException


class AllCharactersRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()

    /**
     * Repository method to make network call that fetches all characters in the series
     */
    suspend fun getAllCharacters() = safeApiCall(
        call = { getCharacters() },
        errorMessage = "Something went wrong. Please tap the icon to refresh"
    )

    private suspend fun getCharacters(): NetworkResult<CharacterList> {
        val response = RetrofitClient.apiService.getAllCharacters()
        return when {
            response.isSuccessful -> {
                Log.e("Remote", "Characters fetched")
                saveAllCharacters((response.body()!!))
                NetworkResult.Success(response.body()!!)
            }
            else -> {
                Log.e("Remote", "Characters not fetched")
                NetworkResult.Error(IOException("Something went wrong. Please tap the icon to refresh"))
            }
        }
    }

    /**
     * Repository method to save all characters in the local sqlite db
     */
    private suspend fun saveAllCharacters(characterArrayList: CharacterList): NetworkResult<List<Long>> {
        val response = characterDAO!!.saveListOfAllCharacters(characterArrayList)
        return when {
            response.isEmpty() -> {
                Log.e("Local:All", "Characters not saved")
                NetworkResult.Error(IOException("Something went wrong. Please tap the icon to refresh"))
            }
            else -> {
                Log.e("Local:All", "Characters saved")
                NetworkResult.Success(response)
            }
        }
    }
}