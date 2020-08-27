package com.anniekobia.harrypotter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.repository.AllCharactersRepository
import com.anniekobia.harrypotter.data.repository.CharacterStaffRepository
import com.anniekobia.harrypotter.data.repository.CharacterStudentRepository


class CharacterViewModel: ViewModel() {

    private val allCharactersRepository = AllCharactersRepository()
    private val characterStudentRepository = CharacterStudentRepository()
    private val characterStaffRepository = CharacterStaffRepository()
    lateinit var characterLiveData: LiveData<ArrayList<Character>>

    fun getAllCharacters(): LiveData<ArrayList<Character>> {
        allCharactersRepository.getAllCharacters()
        characterLiveData = allCharactersRepository.getAllCharactersLiveData()
        return characterLiveData
    }

    fun getStudentCharacters(): LiveData<ArrayList<Character>> {
        characterStudentRepository.getStudentCharacters()
        characterLiveData = characterStudentRepository.getStudentCharacterLiveData()
        return characterLiveData
    }

    fun getStaffCharacters(): LiveData<ArrayList<Character>> {
        characterStaffRepository.getStaffCharacters()
        characterLiveData = characterStaffRepository.getStaffCharactersLiveData()
        return characterLiveData
    }

}
