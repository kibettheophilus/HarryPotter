package com.anniekobia.harrypotter.data.api.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Character(

    @ColumnInfo
    @SerializedName("actor")
    val actor: String,

    @ColumnInfo
    @SerializedName("alive")
    val alive: Boolean,

    @ColumnInfo
    @SerializedName("ancestry")
    val ancestry: String,

    @ColumnInfo
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,

    @ColumnInfo
    @SerializedName("eyeColour")
    val eyeColour: String,

    @ColumnInfo
    @SerializedName("gender")
    val gender: String,

    @ColumnInfo
    @SerializedName("hairColour")
    val hairColour: String,

    @ColumnInfo
    @SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean,

    @ColumnInfo
    @SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean,

    @ColumnInfo
    @SerializedName("house")
    val house: String,

    @ColumnInfo
    @SerializedName("image")
    val image: String,

    @PrimaryKey
    @ColumnInfo
    @SerializedName("name")
    val name: String,

    @ColumnInfo
    @SerializedName("patronus")
    val patronus: String,

    @ColumnInfo
    @SerializedName("species")
    val species: String,

    @Embedded
    @SerializedName("wand")
    val wand: Wand,

    @ColumnInfo
    @SerializedName("yearOfBirth")
    val yearOfBirth: String
): Serializable