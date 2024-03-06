package com.example.word_domain.usecase

import com.example.word_domain.model.WordsList
import com.example.word_domain.repository.WordsListRepository

class DeleteWordUseCase(private val repository : WordsListRepository)
{
    suspend operator fun invoke(word : WordsList) = repository.deleteWord(word = word)
}