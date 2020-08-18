package com.anniekobia.marvel.data.api.model

import java.io.Serializable

data class MarvelSuperhero(
    val superheroAppearanceGender: String,
    val superheroAppearanceRace: String,
    val superheroAliases: List<String>,
    val superheroComics: List<String>,
    val superheroDescription: String,
    val superheroCharacterName: String,
    val superheroName: String,
    val superheroImage: Int
) : Serializable
