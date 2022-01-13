package com.anniekobia.harrypotter.repository

import android.content.Context
import com.anniekobia.harrypotter.data.remote.RetrofitClient
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.data.remote.model.CharacterTwoList
import com.anniekobia.harrypotter.utils.NetworkResult
import com.anniekobia.harrypotter.utils.safeApiCall
import timber.log.Timber
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

    private suspend fun getCharacters(): NetworkResult<CharacterTwoList> {
        val response = RetrofitClient.apiService.getAllCharacters()
        return when {
            response.isSuccessful -> {
                Timber.e("Characters fetched")
                saveAllCharacters((response.body()!!))
                NetworkResult.Success(response.body()!!)
            }
            else -> {
                Timber.e( "Characters not fetched")
                NetworkResult.Error(IOException("Something went wrong. Please tap the icon to refresh"))
            }
        }
    }

    /**
     * Repository method to save all characters in the local sqlite db
     * and retry saving in case the room insert operation fails/returns null
     */
    private suspend fun saveAllCharacters(characterArrayList: CharacterTwoList) {
        val response = characterDAO!!.saveListOfAllCharacters(characterArrayList)
        return when {
            response.isNullOrEmpty() -> {
                Timber.e( "Characters not saved")
                saveAllCharacters(characterArrayList)
            }
            else -> {
                Timber.e("Characters saved")
            }
        }
    }
}