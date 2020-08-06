package com.anniekobia.marvel.utils

import com.anniekobia.marvel.data.MarvelSuperheroDataClassDummy

object MarvelSupeheroDummyData {
    const val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    const val charactername: String = "Chris Hemsworth"
    const val name: String = "Thor"

    val marvelSuperheroesList: ArrayList<MarvelSuperheroDataClassDummy> = arrayListOf<MarvelSuperheroDataClassDummy>(
        MarvelSuperheroDataClassDummy(
             description,
            charactername,
            name
        ),
        MarvelSuperheroDataClassDummy(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDataClassDummy(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDataClassDummy(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDataClassDummy(
            description,
            charactername,
            name
        )
    )
}

