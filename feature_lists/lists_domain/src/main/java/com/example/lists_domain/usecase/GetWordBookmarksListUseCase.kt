package com.example.lists_domain.usecase

import com.example.lists_domain.repository.WordListRepository

class GetWordBookmarksListUseCase(
    private val repository: WordListRepository
)
{
    suspend operator fun invoke() = repository.getWordBookmarksList()
}