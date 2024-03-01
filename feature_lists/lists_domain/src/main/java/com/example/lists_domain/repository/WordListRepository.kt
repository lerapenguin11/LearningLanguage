package com.example.lists_domain.repository

import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.WordList
import kotlinx.coroutines.flow.Flow

interface WordListRepository
{
    suspend fun getWordList(documentId : String): ResultFirestore<List<WordList>>
    suspend fun getWordBookmarksList(): Flow<List<WordList>>
    suspend fun addWord(word : WordList)
    suspend fun deleteWord(word : WordList)
}