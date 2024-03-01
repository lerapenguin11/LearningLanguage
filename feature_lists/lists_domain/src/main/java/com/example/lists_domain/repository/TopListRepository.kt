package com.example.lists_domain.repository

import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.TopList
import com.example.lists_domain.entity.WordList
import kotlinx.coroutines.flow.Flow

interface TopListRepository
{
    suspend fun getTopList(): ResultFirestore<List<TopList>>
}