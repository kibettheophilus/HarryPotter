package com.anniekobia.marvel.utils

object MarvelSuperheroDummyData {
    const val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    const val charactername: String = "Chris Hemsworth"
    const val name: String = "Thor"

    val MARVEL_SUPERHEROES_LIST_DATA_CLASS: ArrayList<MarvelSuperheroDummyDataClass> = arrayListOf<MarvelSuperheroDummyDataClass>(
        MarvelSuperheroDummyDataClass(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDummyDataClass(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDummyDataClass(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDummyDataClass(
            description,
            charactername,
            name
        ),
        MarvelSuperheroDummyDataClass(
            description,
            charactername,
            name
        )
    )
}

