package com.anniekobia.harrypotter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anniekobia.harrypotter.data.remote.model.CharacterList
import com.anniekobia.harrypotter.data.remote.model.Character
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

    /**
     * Fetch list of student characters
     */
    val studentCharacters : LiveData<List<Character>> by lazy {
        characterStudentRepository.getStudentCharacters()
    }
    /**
     * Fetch list of staff characters
     */
    val staffCharacters : LiveData<List<Character>> by lazy {
        characterStaffRepository.getStaffCharacters()
    }

    /**
     * Fetch list of characters who are neither students nor staff
     */
    val otherCharacters : LiveData<List<Character>> by lazy {
        otherCharactersRepository.getOtherCharacters()
    }


    /**
     * ViewModel method that invokes repository method to fetch and save all characters
     */
    fun getAndSaveAllCharacters() {
        viewModelScope.launch {
            allCharacters.postValue(allCharactersRepository.getAllCharacters())
        }
    }

}