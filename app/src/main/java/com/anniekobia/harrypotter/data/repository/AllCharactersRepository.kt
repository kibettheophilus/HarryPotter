package com.anniekobia.harrypotter.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*


class AllCharactersRepository {

    private var allCharactersLiveData = MutableLiveData<ArrayList<Character>>()

    fun getAllCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getAllCharacters()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val characterList = (response.body())!!
                        allCharactersLiveData.postValue(characterList)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                        allCharactersLiveData.postValue(null)
                    }
                } catch (e: HttpException) {
                    Log.e("HttpException: ", "${e.message}")
                    allCharactersLiveData.postValue(null)
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call")
                    allCharactersLiveData.postValue(null)
                }
            }
        }
    }

    fun getAllCharactersLiveData(): LiveData<ArrayList<Character>> {
        return allCharactersLiveData
    }
}