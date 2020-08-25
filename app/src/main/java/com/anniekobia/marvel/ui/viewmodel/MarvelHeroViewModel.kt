package com.anniekobia.marvel.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.anniekobia.marvel.data.repository.MarvelHeroRepository


class MarvelHeroViewModel: ViewModel() {
    private val marvelHeroRepository: MarvelHeroRepository = MarvelHeroRepository()
    lateinit var marvelSuperheroLiveData: LiveData<ArrayList<MarvelSuperhero>>


    fun searchMarvelHero(orderBy: String, limit: Int) {
        marvelHeroRepository.searchMarvelHero(orderBy, limit)
    }

    fun getMarvelHeroLiveData(): LiveData<ArrayList<MarvelSuperhero>> {
        marvelSuperheroLiveData = marvelHeroRepository.getMarvelHeroLiveData()
        return marvelSuperheroLiveData
    }
}
