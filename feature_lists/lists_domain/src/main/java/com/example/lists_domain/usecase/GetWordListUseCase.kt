package com.example.lists_domain.usecase

import com.example.lists_domain.repository.TopListRepository

class GetWordListUseCase(private val repository: TopListRepository)
{
    suspend operator fun invoke(documentId : String) = repository.getWordList(documentId)
}