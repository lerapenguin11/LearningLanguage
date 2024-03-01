package com.example.lists_domain.di

import com.example.lists_domain.repository.TopListRepository
import com.example.lists_domain.usecase.GetTopListWordsUseCase
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
    fun provideGetWordListUseCase(repository: TopListRepository) : GetWordListUseCase{
        return GetWordListUseCase(repository)
    }
}