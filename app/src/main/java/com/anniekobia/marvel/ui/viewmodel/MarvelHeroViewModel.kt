package com.anniekobia.marvel.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.anniekobia.marvel.data.api.model.marvelapi.Marvelhero
import com.anniekobia.marvel.data.repository.MarvelHeroRepository


class MarvelHeroViewModel(application: Application) : AndroidViewModel(application) {
    private var marvelHeroRepository: MarvelHeroRepository? = null
    private var marvelHeroLiveData: LiveData<Marvelhero>? = null


    fun init() {
        marvelHeroRepository = MarvelHeroRepository()
        marvelHeroLiveData = marvelHeroRepository!!.getMarvelHeroLiveData()
    }

    fun searchMarvelHero(orderBy: String?, limit: Int?) {
        marvelHeroRepository?.searchMarvelHero(orderBy, limit)
    }

    fun getMarvelHeroLiveData(): LiveData<Marvelhero>? {
        return marvelHeroLiveData
    }
}
