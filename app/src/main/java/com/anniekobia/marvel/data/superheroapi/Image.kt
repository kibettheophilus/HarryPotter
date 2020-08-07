package com.anniekobia.marvel.data.superheroapi


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    val url: String
)