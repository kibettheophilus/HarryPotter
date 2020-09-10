package com.anniekobia.harrypotter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anniekobia.harrypotter.data.remote.model.Character


@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDAO

    companion object {

        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getCharacterDatabase(context: Context): CharacterDatabase? {
            if (INSTANCE == null) {
                synchronized(CharacterDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CharacterDatabase::class.java, "character_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}

