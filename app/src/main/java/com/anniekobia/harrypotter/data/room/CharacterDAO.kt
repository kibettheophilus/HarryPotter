package com.anniekobia.harrypotter.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.anniekobia.harrypotter.data.api.model.Character

@Dao
interface CharacterDAO {
    //Insert one character to database
    @Insert(onConflict = REPLACE)
    fun saveCharacter(character: Character)

    //Insert list of all character to database
    @Insert
    fun saveListOfAllCharacters(characterList: List<Character>)

    //Fetch list of all characters who are students from database
    @Query("SELECT * FROM character WHERE hogwartsStudent = 1")
    fun getStudentCharacters(): LiveData<List<Character>>

    //Fetch list of all characters who are staff from database
    @Query("SELECT * FROM character WHERE hogwartsStaff = 1")
    fun getStaffCharacters(): LiveData<List<Character>>

    //Fetch list of all characters who are neither staff nor students from database
    @Query("SELECT * FROM character WHERE hogwartsStudent = 0 and hogwartsStaff = 0")
    fun getOtherCharacters(): LiveData<List<Character>>
}
