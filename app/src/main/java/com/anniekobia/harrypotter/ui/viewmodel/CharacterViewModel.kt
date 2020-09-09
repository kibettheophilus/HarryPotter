package com.anniekobia.harrypotter.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
    lateinit var characterLiveData: LiveData<ArrayList<Character>>
    val characters = MutableLiveData<NetworkResult<CharacterList>>()
    val charactersResponse = MutableLiveData<CharacterList>()
    val characterError = MutableLiveData<String>()

    fun getOtherCharacters(): LiveData<ArrayList<Character>> {
        characterLiveData = otherCharactersRepository.getOtherCharactersLiveData()
        return characterLiveData
    }

//    fun getStudentCharacters(): LiveData<ArrayList<Character>> {
//        characterLiveData = characterStudentRepository.getStudentCharacterLiveData()
//        return characterLiveData
//    }

//    fun getStaffCharacters(): LiveData<ArrayList<Character>> {
//        characterLiveData = characterStaffRepository.getStaffCharactersLiveData()
//        return characterLiveData
//    }

//    fun getAndSaveAllCharacters(){
//        allCharactersRepository.getAllCharacters()
//    }


    /**
     * ViewModel method that invokes repository method to fetch and save all characters
     */
    fun getAndSaveAllCharacters(){
        viewModelScope.launch {
            characters.postValue(allCharactersRepository.getAllCharacters())
        }
    }

//    fun getAndSaveAllCharacters() {
//        viewModelScope.launch {
//            when (val value = allCharactersRepository.getAllCharacters()) {
//                is NetworkResult.Success -> charactersResponse.postValue(value.data)
//                is NetworkResult.Error -> characterError.postValue(value.exception.message)
//            }
//        }
//    }
}