package com.anniekobia.harrypotter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.data.remote.model.Character

class CharacterStudentRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()


    /**
     * Repository method to get all characters who are students from the local sqlite db
     */
    fun getStudentCharacters(): LiveData<List<Character>> = characterDAO!!.getStudentCharacters()

}