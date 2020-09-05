package com.anniekobia.harrypotter.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.local.CharacterDatabase


class OtherCharactersRepository(context: Context) {
    // Review : Consider adding a DI Framework
    private val database = CharacterDatabase.getCharacterDatabase(context = context)
    private val characterDAO = database?.characterDao()
    private lateinit var otherCharactersLiveData: LiveData<ArrayList<Character>>


    fun getOtherCharactersLiveData(): LiveData<ArrayList<Character>> {
        // Review : No need to smart cast this, make the DAO return live data and/or flow
        otherCharactersLiveData =
            characterDAO!!.getOtherCharacters() as LiveData<ArrayList<Character>>
        return otherCharactersLiveData
    }
}