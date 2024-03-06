package com.example.word_data.di

import com.example.word_data.repository.WordsListRepositoryImpl
import com.example.word_data.room.WordsListDao
import com.example.word_domain.repository.WordsListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object WordDataModule
{
    @Provides
    fun provideWordListRepository(wordsDao : WordsListDao) : WordsListRepository{
        return WordsListRepositoryImpl(wordsDao)
    }
}