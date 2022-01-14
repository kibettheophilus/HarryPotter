package com.anniekobia.harrypotter.data.remote.model

import java.io.Serializable

data class CharacterDetailedData(
    val actor: String,

    val alive: Boolean,

    val ancestry: String,

    val dateOfBirth: String,

    val eyeColour: String,

    val gender: String,

    val hairColour: String,

    val hogwartsStaff: Boolean,

    val hogwartsStudent: Boolean,

    val house: String,

    val image: String,

    val name: String,

    val patronus: String,

    val species: String,

    val wand: Wand,

    val yearOfBirth: String,

    val wizard: Boolean,

    val alternate_actors: List<Any>,

    val alternate_names: List<Any>,
) : Serializable