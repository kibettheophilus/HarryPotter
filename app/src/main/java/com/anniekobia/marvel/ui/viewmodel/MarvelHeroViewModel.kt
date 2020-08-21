package com.anniekobia.marvel.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.anniekobia.marvel.data.repository.MarvelHeroRepository


class MarvelHeroViewModel(application: Application) : AndroidViewModel(application) {
    private var marvelHeroRepository: MarvelHeroRepository? = null
    private var marvelSuperheroLiveData: LiveData<ArrayList<MarvelSuperhero>>? = null


    fun init() {
        marvelHeroRepository = MarvelHeroRepository()
        marvelSuperheroLiveData = marvelHeroRepository!!.getMarvelHeroLiveData()
    }

    fun searchMarvelHero(orderBy: String?, limit: Int?) {
        marvelHeroRepository?.searchMarvelHero(orderBy, limit)
    }

    fun getMarvelHeroLiveData(): LiveData<ArrayList<MarvelSuperhero>>? {
        return marvelSuperheroLiveData
    }
}
