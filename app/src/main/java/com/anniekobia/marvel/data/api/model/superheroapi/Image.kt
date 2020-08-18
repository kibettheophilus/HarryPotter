package com.anniekobia.marvel.data.api.model.superheroapi


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    val url: String
)