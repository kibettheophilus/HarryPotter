package com.anniekobia.marvel.data.api.marvelapi


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int
)