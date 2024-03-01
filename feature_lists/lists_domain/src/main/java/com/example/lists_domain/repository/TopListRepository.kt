package com.example.lists_domain.repository

import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.TopList
import com.example.lists_domain.entity.WordList

interface TopListRepository
{
    suspend fun getTopList(): ResultFirestore<List<TopList>>
    suspend fun getWordList(documentId : String): ResultFirestore<List<WordList>>
}