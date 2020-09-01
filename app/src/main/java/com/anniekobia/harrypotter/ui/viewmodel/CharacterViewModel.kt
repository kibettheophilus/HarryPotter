package com.anniekobia.harrypotter.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.repository.AllCharactersRepository
import com.anniekobia.harrypotter.data.repository.OtherCharactersRepository
import com.anniekobia.harrypotter.data.repository.CharacterStaffRepository
import com.anniekobia.harrypotter.data.repository.CharacterStudentRepository


class CharacterViewModel(application: Application): AndroidViewModel(application) {

    private val allCharactersRepository = AllCharactersRepository(application)
    private val otherCharactersRepository = OtherCharactersRepository(application)
    private val characterStudentRepository = CharacterStudentRepository(application)
    private val characterStaffRepository = CharacterStaffRepository(application)
    lateinit var characterLiveData: LiveData<ArrayList<Character>>

    fun getOtherCharacters(): LiveData<ArrayList<Character>> {
        characterLiveData = otherCharactersRepository.getOtherCharactersLiveData()
        return characterLiveData
    }

    fun getStudentCharacters(): LiveData<ArrayList<Character>> {
        characterLiveData = characterStudentRepository.getStudentCharacterLiveData()
        return characterLiveData
    }

    fun getStaffCharacters(): LiveData<ArrayList<Character>> {
        characterLiveData = characterStaffRepository.getStaffCharactersLiveData()
        return characterLiveData
    }

    fun getAndSaveAllCharacters(){
        allCharactersRepository.getAllCharacters()
    }
}