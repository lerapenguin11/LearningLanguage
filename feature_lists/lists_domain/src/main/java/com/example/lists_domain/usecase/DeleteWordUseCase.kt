package com.example.lists_domain.usecase

import com.example.lists_domain.entity.WordList
import com.example.lists_domain.repository.WordListRepository

class DeleteWordUseCase (
    private val repository: WordListRepository
)
{
    suspend operator fun invoke(word : WordList) = repository.deleteWord(word)
}