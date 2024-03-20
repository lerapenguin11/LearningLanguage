package com.example.detailed_domain.usecase

import com.example.detailed_domain.repository.WordDetailedRepository

class GetWordDetailedUseCase(private val repository : WordDetailedRepository)
{
    suspend operator fun invoke(wordId : Int) = repository.getWord(wordId = wordId)
}