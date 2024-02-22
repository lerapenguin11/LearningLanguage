package com.example.lists_domain.di

import com.example.lists_domain.repository.TopListRepository
import com.example.lists_domain.usecase.GetTopListWordsUseCase
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
}