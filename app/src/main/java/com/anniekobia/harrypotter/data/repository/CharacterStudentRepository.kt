package com.anniekobia.harrypotter.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.room.CharacterDatabase

class CharacterStudentRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private lateinit var characterStudentLiveData: LiveData<ArrayList<Character>>


    fun getStudentCharacterLiveData(): LiveData<ArrayList<Character>> {
        characterStudentLiveData =
            characterDAO!!.getStudentCharacters() as LiveData<ArrayList<Character>>
        return characterStudentLiveData
    }
}