package com.anniekobia.marvel.utils

import com.anniekobia.marvel.R

object MarvelSuperheroDummyData {
    const val image: Int = R.drawable.blackpanther
    const val description: String =
        "As the Norse God of thunder and lightning, Thor wields one of the greatest weapons ever made, the enchanted hammer Mjolnir. While others have described Thor as an over-muscled, oafish imbecile, he's quite smart and compassionate.  He's self-assured, and he would never, ever stop fighting for a worthwhile cause."
    const val charactername: String = "Chris Hemsworth"
    const val name: String = "Thor"
    const val gender: String = "Male"
    const val race: String = "Human"
    val aliases: List<String> = listOf(
        "God of Thunder",
        "Siegmund",
        "Odinson"
    )
    val comics: List<String> = listOf(
        "A+X (2012)",
        "A+X (2012)",
        "Age of Heroes (2010)",
        "Alpha: Big Time (2013)",
        "Alpha Flight (1983)",
        "The Amazing Spider-Man (1963)"
    )

    val MARVEL_SUPERHEROES_LIST_DATA_CLASS: ArrayList<MarvelSuperheroDummyDataClass> =
        arrayListOf<MarvelSuperheroDummyDataClass>(
            MarvelSuperheroDummyDataClass(
                gender,
                race,
                aliases,
                comics,
                description,
                charactername,
                name,
                image
            ),
            MarvelSuperheroDummyDataClass(
                gender,
                race,
                aliases,
                comics,
                description,
                charactername,
                name,
                image
            ),
            MarvelSuperheroDummyDataClass(
                gender,
                race,
                aliases,
                comics,
                description,
                charactername,
                name,
                image
            ),
            MarvelSuperheroDummyDataClass(
                gender,
                race,
                aliases,
                comics,
                description,
                charactername,
                name,
                image
            ),
            MarvelSuperheroDummyDataClass(
                gender,
                race,
                aliases,
                comics,
                description,
                charactername,
                name,
                image
            )
        )
}

