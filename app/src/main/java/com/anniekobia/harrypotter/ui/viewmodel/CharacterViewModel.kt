package com.anniekobia.harrypotter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.data.repository.CharacterStaffRepository
import com.anniekobia.harrypotter.data.repository.CharacterStudentRepository


class CharacterViewModel: ViewModel() {

    private val characterStudentRepository = CharacterStudentRepository()
    private val characterStaffRepository = CharacterStaffRepository()
    lateinit var characterLiveData: LiveData<ArrayList<Character>>

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
