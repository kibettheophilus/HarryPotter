package com.anniekobia.marvel.data.marvelapi


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)