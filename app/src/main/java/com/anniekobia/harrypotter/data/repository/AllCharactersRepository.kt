package com.anniekobia.harrypotter.data.repository

import android.content.Context
import android.util.Log
import com.anniekobia.harrypotter.data.retrofit.RetrofitClient
import com.anniekobia.harrypotter.data.room.CharacterDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*


class AllCharactersRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()


    fun getAllCharacters() {
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
}