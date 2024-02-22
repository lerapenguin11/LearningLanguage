package com.example.lists_domain.usecase

import com.example.lists_domain.repository.TopListRepository

class GetTopListWordsUseCase(private val repository: TopListRepository)
{
    suspend operator fun invoke() = repository.getTopList()
}