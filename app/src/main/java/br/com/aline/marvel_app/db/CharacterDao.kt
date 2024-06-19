package br.com.aline.marvel_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.aline.marvel_app.domain.model.Character
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(character: Character):Long

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<Character>>

    @Delete
    suspend fun deleteCharacter(character: Character)
}