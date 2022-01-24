package com.anniekobia.harrypotter.repository

import android.content.Context
import com.anniekobia.harrypotter.data.remote.RetrofitClient
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.remote.model.CharacterList
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

    private suspend fun getCharacters(): NetworkResult<CharacterList> {
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
    private suspend fun saveAllCharacters(characterArrayList: CharacterList) {
        val allCharacters = arrayListOf<Character>()
        for(c in characterArrayList){
            if (c.name.isNotBlank()&&c.image.isNotBlank()){
                val character = Character(c.wizard,c.actor,c.alive,c.ancestry,c.dateOfBirth,c.eyeColour,c.gender, c.hairColour,c.hogwartsStaff,c.hogwartsStudent,c.house,c.image,c.name,c.patronus,c.species,c.wand,c.yearOfBirth)
                //val character = Character(c.actor,c.alive,c.ancestry,c.dateOfBirth,c.eyeColour,c.gender,c.hairColour,c.hogwartsStaff,c.hogwartsStudent,c.house,c.image,c.name,c.patronus,c.species,c.wand,c.yearOfBirth)
                allCharacters.add(character)
            }
        }
        val response = characterDAO!!.saveListOfAllCharacters(allCharacters)
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