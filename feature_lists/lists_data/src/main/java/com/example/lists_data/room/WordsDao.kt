package com.example.lists_data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lists_data.entities.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWord(word : WordEntity)

    @Query("SELECT * FROM word_bookmark")
    fun getSavedWord(): Flow<List<WordEntity>>

    @Delete
    suspend fun deleteWord(word : WordEntity)
}