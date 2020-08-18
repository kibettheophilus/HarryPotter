package com.anniekobia.marvel.data.api.superheroapi


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    val url: String
)