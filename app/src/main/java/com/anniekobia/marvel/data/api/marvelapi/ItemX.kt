package com.anniekobia.marvel.data.api.marvelapi


import com.google.gson.annotations.SerializedName

data class ItemX(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)