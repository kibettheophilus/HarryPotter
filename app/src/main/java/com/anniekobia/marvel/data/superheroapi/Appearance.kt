package com.anniekobia.marvel.data.superheroapi


import com.google.gson.annotations.SerializedName

data class Appearance(
    @SerializedName("eye-color")
    val eyeColor: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair-color")
    val hairColor: String,
    @SerializedName("height")
    val height: List<String>,
    @SerializedName("race")
    val race: String,
    @SerializedName("weight")
    val weight: List<String>
)