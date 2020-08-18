package com.anniekobia.marvel.data.api.model.marvelapi


import com.google.gson.annotations.SerializedName

data class ItemXX(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)