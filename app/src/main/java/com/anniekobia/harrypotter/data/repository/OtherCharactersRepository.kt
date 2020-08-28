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


class OtherCharactersRepository {

    private var otherCharactersLiveData = MutableLiveData<ArrayList<Character>>()

    //Get all characters and filter out students and staff
    fun getOtherCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getAllCharacters()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val characterList = (response.body())!!
                        val otherCharactersList = filterOutStudentsAndStaff(characterList)
                        otherCharactersLiveData.postValue(otherCharactersList)
                    } else {
                        Log.e("Error: ", "${response.code()}")
                        otherCharactersLiveData.postValue(null)
                    }
                } catch (e: HttpException) {
                    Log.e("HttpException: ", "${e.message}")
                    otherCharactersLiveData.postValue(null)
                } catch (e: Throwable) {
                    Log.e("Failed", "Overall Failure on Marvel API call")
                    otherCharactersLiveData.postValue(null)
                }
            }
        }
    }

    private fun filterOutStudentsAndStaff(characterList: ArrayList<Character>):  ArrayList<Character>{
        val otherCharacters = ArrayList<Character>()
        for (character in characterList){
            if (!character.hogwartsStaff and !character.hogwartsStudent) otherCharacters.add(character)
        }
        return  otherCharacters
    }

    fun getOtherCharactersLiveData(): LiveData<ArrayList<Character>> {
        return otherCharactersLiveData
    }
}