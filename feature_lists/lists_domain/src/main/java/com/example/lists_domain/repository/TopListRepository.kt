package com.example.lists_domain.repository

import com.example.common_utils.common.ResultFirestore
import com.example.lists_domain.entity.TopList

interface TopListRepository
{
    suspend fun getTopList(): ResultFirestore<List<TopList>>
}