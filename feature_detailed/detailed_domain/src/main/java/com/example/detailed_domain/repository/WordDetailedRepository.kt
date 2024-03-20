package com.example.detailed_domain.repository

import com.example.detailed_domain.model.WordDetailed
import kotlinx.coroutines.flow.Flow

interface WordDetailedRepository
{
    suspend fun getWord(wordId : Int) : WordDetailed
    suspend fun deleteWord(word : WordDetailed)
    suspend fun updateWord(word : WordDetailed)
}