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


class CharacterStudentRepository {

    private var characterStudentLiveData = MutableLiveData<ArrayList<Character>>()

    fun getStudentCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getStudentCharacters()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val characterList = (response.body())!!
                        characterStudentLiveData.postValue(characterList)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                        characterStudentLiveData.postValue(null)
                    }
                } catch (e: HttpException) {
                    Log.e("HttpException: ", "${e.message}")
                    characterStudentLiveData.postValue(null)
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call")
                    characterStudentLiveData.postValue(null)
                }
            }
        }
    }

    fun getStudentCharacterLiveData(): LiveData<ArrayList<Character>> {
        return characterStudentLiveData
    }
}