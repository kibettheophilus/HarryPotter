package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.remote.RetrofitClient
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.data.remote.model.CharacterList
import com.anniekobia.harrypotter.utils.NetworkResult
import java.io.IOException


class AllCharactersRepository(context: Context) {
    // Review : Repo layer should be outside data layer
    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()


//    fun getAllCharacters() {
//        // Review:  you can use a suspend function
//        // Review:  use a generic method 'safeApi' which has a try catch block instead of using try
//        // catch for every call
//        // Use a sealed class to handle responses from the network
//        CoroutineScope(Dispatchers.Main).launch {
//            val response = RetrofitClient.apiService.getAllCharacters()
//
//            withContext(Dispatchers.IO) {
//                try {
//                    if (response.isSuccessful) {
//                        val characterList = (response.body())!!
//                        characterDAO!!.saveListOfAllCharacters(characterList)
//                    } else {
//                        Log.e("Error: ", "${response.code()}")
//                    }
//                } catch (e: HttpException) {
//                    Log.e("HttpException: ", "${e.message}")
//                } catch (e: Throwable) {
//                    Log.e("Failed", "Overall Failure on Marvel API call" + e.message)
//                }
//            }
//        }
//    }


//    suspend fun room(){
//        //getAllCharacters() { // logic to check updates}
//        //return from Room
//    }
//     get all characters from room
//     check if list is empty
//     if empty fetch from network
//     if not empty return list


    /**
     * Repository method to make network call that fetches all characters in the series
     */
    suspend fun getAllCharacters(): NetworkResult<CharacterList> {
        val response = RetrofitClient.apiService.getAllCharacters()
        return when {
            response.isSuccessful -> {
                Log.e("Remote", "Characters fetched")
                saveAllCharacters((response.body()!!))
                NetworkResult.Success(response.body()!!)
            }
            else -> {
                Log.e("Remote", "Characters not fetched")
                NetworkResult.Error(IOException("Something went wrong. Check your internet connection and try again"))
            }
        }
    }

    /**
     * Repository method to save all characters in the local sqlite db
     */
    suspend fun saveAllCharacters(characterArrayList: CharacterList): NetworkResult<List<Long>> {
        val response = characterDAO!!.saveListOfAllCharacters(characterArrayList)
        return when {
            response.isEmpty() -> {
                Log.e("Local", "Characters saved")
                NetworkResult.Error(IOException("Something went wrong. Check your internet connection and try again"))
            }
            else -> {
                Log.e("Local", "Characters saved")
                NetworkResult.Success(response)
            }
        }
    }
//    private suspend fun getChar(): NetworkResult<CharacterUIModel> {
//        val response = RetrofitClient.apiService.getAllCharacters()
//        return when {
//            response.isSuccessful -> NetworkResult.Success(response.body()!![0].toResponse())
//            else -> NetworkResult.Error(IOException("Could not fetch characters"))
//        }
//    }

}