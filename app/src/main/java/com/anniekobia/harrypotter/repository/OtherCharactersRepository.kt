package com.anniekobia.harrypotter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase
import com.anniekobia.harrypotter.data.remote.model.CharacterTwoListItem

class OtherCharactersRepository(context: Context) {

    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()


    /**
     * Repository method to get all characters who are neither students nor staff from the local sqlite db
     */
    fun getOtherCharacters(): LiveData<List<CharacterTwoListItem>> = characterDAO!!.getOtherCharacters()

}