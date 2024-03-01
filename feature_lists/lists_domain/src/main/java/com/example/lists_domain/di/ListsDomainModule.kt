package com.example.lists_domain.di

import com.example.lists_domain.repository.TopListRepository
import com.example.lists_domain.repository.WordListRepository
import com.example.lists_domain.usecase.AddWordUseCase
import com.example.lists_domain.usecase.DeleteWordUseCase
import com.example.lists_domain.usecase.GetTopListWordsUseCase
import com.example.lists_domain.usecase.GetWordBookmarksListUseCase
import com.example.lists_domain.usecase.GetWordListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ListsDomainModule
{
    @Provides
    fun provideGetTopListUseCase(repository: TopListRepository) : GetTopListWordsUseCase {
        return GetTopListWordsUseCase(repository)
    }

    @Provides
    fun provideGetWordListUseCase(repository: WordListRepository) : GetWordListUseCase{
        return GetWordListUseCase(repository)
    }

    @Provides
    fun provideGetWordBookmarksListUseCase(repository : WordListRepository)
    : GetWordBookmarksListUseCase{
        return GetWordBookmarksListUseCase(repository)
    }

    @Provides
    fun provideAddWordUseCase(repository: WordListRepository) : AddWordUseCase{
        return AddWordUseCase(repository)
    }

    @Provides
    fun provideDeleteWordUseCase(repository: WordListRepository) : DeleteWordUseCase{
        return DeleteWordUseCase(repository)
    }
}