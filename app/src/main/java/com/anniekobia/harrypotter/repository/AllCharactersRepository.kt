package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.remote.model.CharacterUIModel
import com.anniekobia.harrypotter.data.remote.RetrofitClient
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.utils.NetworkResult
import com.anniekobia.harrypotter.utils.safeApiCall
import com.anniekobia.harrypotter.utils.toResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import java.io.IOException


class AllCharactersRepository(context: Context) {
    // Review : Repo layer should be outside data layer
    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()


    fun getAllCharacters() {
        // Review:  you can use a suspend function
        // Review:  use a generic method 'safeApi' which has a try catch block instead of using try
        // catch for every call
        // Use a sealed class to handle responses from the network
        CoroutineScope(Dispatchers.Main).launch {
            val response = RetrofitClient.apiService.getAllCharacters()

            withContext(Dispatchers.IO) {
                try {
                    if (response.isSuccessful) {
                        val characterList = (response.body())!!
                        characterDAO!!.saveListOfAllCharacters(characterList)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                    }
                } catch (e: HttpException) {
                    Log.e("HttpException: ", "${e.message}")
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call" + e.message)
                }
            }
        }
    }

    suspend fun getCharacters() {
        val response = RetrofitClient.apiService.getAllCharacters()
        try {
            if (response.isSuccessful) {
                val characterList = (response.body())!!
                characterDAO!!.saveListOfAllCharacters(characterList)
            } else {
                Log.e("Error: ", "${response.code()}")
            }
        } catch (e: HttpException) {
            Log.e("HttpException: ", "${e.message}")
        } catch (e: Throwable) {
            Log.e("Failed", "Overall Failure on Marvel API call" + e.message)
        }

    }

    suspend fun room(){
        //getAllCharacters() { // logic to check updates}
        //return from Room
    }
    // get all characters from room
    // check if list is empty
    // if empty fetch from network
    // if not empty return list

    suspend fun fetchCharacters() = safeApiCall(
        call = { getChar() },
        errorMessage = "An error occurred"
    )

    private suspend fun getChar(): NetworkResult<CharacterUIModel> {
        val response = RetrofitClient.apiService.getAllCharacters()
        return when {
            response.isSuccessful -> NetworkResult.Success(response.body()!![0].toResponse())
            else -> NetworkResult.Error(IOException("Could not fetch characters"))
        }
    }


    suspend fun getOneCharacter() {
        val response = RetrofitClient.apiService.getAllCharacters()
        try {
            if (response.isSuccessful) {
                val characterList = (response.body())!!
                characterDAO!!.saveListOfAllCharacters(characterList)
            } else {
                Log.e("Error: ", "${response.code()}")
            }
        } catch (e: HttpException) {
            Log.e("HttpException: ", "${e.message}")
        } catch (e: Throwable) {
            Log.e("Failed", "Overall Failure on Marvel API call" + e.message)
        }
    }
}