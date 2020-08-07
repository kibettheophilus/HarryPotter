package com.anniekobia.marvel.data.superheroapi


import com.google.gson.annotations.SerializedName

data class Powerstats(
    @SerializedName("combat")
    val combat: String,
    @SerializedName("durability")
    val durability: String,
    @SerializedName("intelligence")
    val intelligence: String,
    @SerializedName("power")
    val power: String,
    @SerializedName("speed")
    val speed: String,
    @SerializedName("strength")
    val strength: String
)