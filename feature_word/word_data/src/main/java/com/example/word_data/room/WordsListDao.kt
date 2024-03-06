package com.example.word_data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.lists_data.entities.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsListDao
{
    @Query("SELECT * FROM word_bookmark")
    fun getSavedWord(): Flow<List<WordEntity>>

    @Delete
    suspend fun deleteWord(word : WordEntity)
}