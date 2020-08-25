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


class CharacterStaffRepository {

    private var characterStaffLiveData = MutableLiveData<ArrayList<Character>>()

    fun getStaffCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getStaffCharacters()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val characterList = (response.body())!!
                        characterStaffLiveData.postValue(characterList)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                        characterStaffLiveData.postValue(null)
                    }
                } catch (e: HttpException) {
                    Log.e("HttpException: ", "${e.message}")
                    characterStaffLiveData.postValue(null)
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call")
                    characterStaffLiveData.postValue(null)
                }
            }
        }
    }

    fun getStaffCharactersLiveData(): LiveData<ArrayList<Character>> {
        return characterStaffLiveData
    }
}