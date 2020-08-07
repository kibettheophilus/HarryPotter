package com.anniekobia.marvel.data.superheroapi


import com.google.gson.annotations.SerializedName

data class Biography(
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("alignment")
    val alignment: String,
    @SerializedName("alter-egos")
    val alterEgos: String,
    @SerializedName("first-appearance")
    val firstAppearance: String,
    @SerializedName("full-name")
    val fullName: String,
    @SerializedName("place-of-birth")
    val placeOfBirth: String,
    @SerializedName("publisher")
    val publisher: String
)