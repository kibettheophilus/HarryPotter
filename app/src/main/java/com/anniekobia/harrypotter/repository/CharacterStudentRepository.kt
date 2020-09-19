package com.anniekobia.harrypotter.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.utils.NetworkResult
import java.io.IOException

class CharacterStudentRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private val allCharactersRepository = AllCharactersRepository(context)

    /**
     * Repository method to get all characters who are students from the local sqlite db
     * if no data in room, fetch all characters and tell user to refresh
     */
    fun getStudentCharacters(): LiveData<List<Character>> = characterDAO!!.getStudentCharacters()

}