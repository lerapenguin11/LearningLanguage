package com.example.word_domain.repository

import com.example.word_domain.model.WordsList
import kotlinx.coroutines.flow.Flow

interface WordsListRepository
{
    suspend fun getWordList(): Flow<List<WordsList>>
    suspend fun deleteWord(word : WordsList)
}