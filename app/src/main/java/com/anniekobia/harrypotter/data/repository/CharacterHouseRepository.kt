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


class CharacterHouseRepository {

    private var characterHouseLiveData = MutableLiveData<ArrayList<Character>>()

    fun getCharactersInHouse(house: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getCharactersInHouse(house)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val characterList = (response.body())!!
                        characterHouseLiveData.postValue(characterList)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                        characterHouseLiveData.postValue(null)
                    }
                } catch (e: HttpException) {
                    Log.e("HttpException: ", "${e.message}")
                    characterHouseLiveData.postValue(null)
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call")
                    characterHouseLiveData.postValue(null)
                }
            }
        }
    }

    fun getStaffCharactersLiveData(): LiveData<ArrayList<Character>> {
        return characterHouseLiveData
    }
}