package com.example.detailed_data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.lists_data.entities.WordEntity

@Dao
interface WordDetailedDao
{
    @Query("SELECT * FROM word_bookmark WHERE id = :wordId")
    suspend fun getWordDetailed(wordId : Int) : WordEntity
    @Delete
    suspend fun deleteWordDetailed(word : WordEntity)
    @Update
    suspend fun updateWordDetailed(word: WordEntity)
}