package com.anniekobia.harrypotter.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anniekobia.harrypotter.data.remote.model.Character

@Dao
interface CharacterDAO {


    /**
     * Insert list of all characterS to the local sqlite database
     */
    @Insert
    suspend fun saveListOfAllCharacters(characterList: List<Character>): List<Long>

    /**
     * Fetch list of all characters who are students from database
     */
    @Query("SELECT * FROM character WHERE hogwartsStudent = 1")
    fun getStudentCharacters(): LiveData<List<Character>>

    /**
     * Fetch list of all characters who are staff from database
     */
    @Query("SELECT * FROM character WHERE hogwartsStaff = 1")
    fun getStaffCharacters(): LiveData<List<Character>>

    /**
     * Fetch list of all characters who are neither staff nor students from database
     */
    @Query("SELECT * FROM character WHERE hogwartsStudent = 0 and hogwartsStaff = 0")
    fun getOtherCharacters(): LiveData<List<Character>>
}
