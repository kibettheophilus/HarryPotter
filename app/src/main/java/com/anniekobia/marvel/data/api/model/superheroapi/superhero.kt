package com.anniekobia.marvel.data.api.model.superheroapi


import com.google.gson.annotations.SerializedName

data class superhero(
    @SerializedName("response")
    val response: String,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("results-for")
    val resultsFor: String
)