package com.example.word_domain.repository

import com.example.word_domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordsListRepository
{
    suspend fun getWordList(): Flow<List<Word>>
    suspend fun deleteWord(word : Word)
}