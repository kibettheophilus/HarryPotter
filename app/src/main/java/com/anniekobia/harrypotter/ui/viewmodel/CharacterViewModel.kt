package com.anniekobia.harrypotter.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.remote.model.CharacterList
import com.anniekobia.harrypotter.repository.AllCharactersRepository
import com.anniekobia.harrypotter.repository.OtherCharactersRepository
import com.anniekobia.harrypotter.repository.CharacterStaffRepository
import com.anniekobia.harrypotter.repository.CharacterStudentRepository
import com.anniekobia.harrypotter.utils.NetworkResult
import kotlinx.coroutines.launch


class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val allCharactersRepository = AllCharactersRepository(application)
    private val otherCharactersRepository = OtherCharactersRepository(application)
    private val characterStudentRepository = CharacterStudentRepository(application)
    private val characterStaffRepository = CharacterStaffRepository(application)
    val allCharacters = MutableLiveData<NetworkResult<CharacterList>>()
    val characters = MutableLiveData<NetworkResult<List<Character>>>()

    /**
     * ViewModel method that invokes repository method to fetch and save all characters
     */
    fun getAndSaveAllCharacters() {
        viewModelScope.launch {
            allCharacters.postValue(allCharactersRepository.getAllCharacters())
        }
    }

    /**
     * ViewModel method that invokes repository method to fetch student characters
     */
    fun getStudentCharacters() {
        viewModelScope.launch {
            characters.postValue(characterStudentRepository.getStudentCharacters())
        }
    }

    /**
     * ViewModel method that invokes repository method to fetch staff characters
     */
    fun getStaffCharacters() {
        viewModelScope.launch {
            characters.postValue(characterStaffRepository.getStaffCharacters())
        }
    }

    /**
     * ViewModel method that invokes repository method to fetch other characters that are not students nor staff
     */
    fun getOtherCharacters() {
        viewModelScope.launch {
            characters.postValue(otherCharactersRepository.getOtherCharacters())
        }
    }

}