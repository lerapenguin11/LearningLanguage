package com.example.word_domain.usecase

import com.example.word_domain.model.Word
import com.example.word_domain.repository.WordsListRepository

class DeleteWordUseCase(private val repository : WordsListRepository)
{
    suspend operator fun invoke(word : Word) = repository.deleteWord(word = word)
}