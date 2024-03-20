package com.example.detailed_domain.usecase

import com.example.detailed_domain.model.WordDetailed
import com.example.detailed_domain.repository.WordDetailedRepository

class DeleteWordDetailedUseCase(private val repository: WordDetailedRepository)
{
    suspend operator fun invoke(word : WordDetailed) = repository.deleteWord(word = word)
}