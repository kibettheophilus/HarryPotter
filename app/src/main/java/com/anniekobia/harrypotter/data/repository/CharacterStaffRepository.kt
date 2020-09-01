package com.anniekobia.harrypotter.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.room.CharacterDatabase


class CharacterStaffRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private lateinit var characterStaffLiveData: LiveData<ArrayList<Character>>

    fun getStaffCharactersLiveData(): LiveData<ArrayList<Character>> {
        characterStaffLiveData =
            characterDAO!!.getStaffCharacters() as LiveData<ArrayList<Character>>
        return characterStaffLiveData
    }
}