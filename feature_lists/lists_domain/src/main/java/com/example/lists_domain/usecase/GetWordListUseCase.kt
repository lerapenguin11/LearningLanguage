package com.example.lists_domain.usecase

import com.example.lists_domain.repository.WordListRepository

class GetWordListUseCase(private val repository: WordListRepository)
{
    suspend operator fun invoke(documentId : String) = repository.getWordList(documentId)
}