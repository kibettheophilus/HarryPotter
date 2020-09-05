package com.anniekobia.harrypotter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase

class CharacterStudentRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    // Review: Not really necessary


    fun getStudentCharacterLiveData(): LiveData<List<Character>> =
        characterDAO!!.getStudentCharacters()

}