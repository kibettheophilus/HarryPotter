package com.anniekobia.harrypotter.data.remote.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Wand(
    @SerializedName("core")
    val core: String,
    @SerializedName("length")
    val length: String,
    @SerializedName("wood")
    val wood: String
)