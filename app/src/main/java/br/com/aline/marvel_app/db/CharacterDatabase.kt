package br.com.aline.marvel_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.aline.marvel_app.domain.model.Character

@Database(entities = [Character::class],version = 1)

abstract class CharacterDatabase:RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao

    companion object{
        @Volatile
        private var instance: CharacterDatabase? = null
        private val LOCK = Any()

        operator fun invoke (context: Context) = instance ?:
        synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it

            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CharacterDatabase::class.java,
                "character_db"
            ).build()
    }
}