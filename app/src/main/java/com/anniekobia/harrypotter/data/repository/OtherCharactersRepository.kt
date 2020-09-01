package com.anniekobia.harrypotter.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.room.CharacterDatabase


class OtherCharactersRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private lateinit var otherCharactersLiveData: LiveData<ArrayList<Character>>


    fun getOtherCharactersLiveData(): LiveData<ArrayList<Character>> {
        otherCharactersLiveData =
            characterDAO!!.getOtherCharacters() as LiveData<ArrayList<Character>>
        return otherCharactersLiveData
    }
}