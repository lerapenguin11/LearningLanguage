package com.example.word_domain.di

import com.example.word_domain.repository.WordsListRepository
import com.example.word_domain.usecase.DeleteWordUseCase
import com.example.word_domain.usecase.GetWordsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object WordDomainModule
{
    @Provides
    fun provideGetWordsListUseCase(repository : WordsListRepository) : GetWordsListUseCase {
        return GetWordsListUseCase(repository)
    }

    @Provides
    fun provideDeleteWordUseCase(repository : WordsListRepository) : DeleteWordUseCase {
        return DeleteWordUseCase(repository)
    }
}