package com.example.word_domain.usecase

import com.example.word_domain.repository.WordsListRepository

class GetWordsListUseCase(private val repository: WordsListRepository)
{
    suspend operator fun invoke() = repository.getWordList()
}