package com.example.lists_data.di

import com.example.lists_data.repository.TopListRepositoryImpl
import com.example.lists_data.repository.WordListRepositoryImpl
import com.example.lists_data.room.WordsDao
import com.example.lists_domain.repository.TopListRepository
import com.example.lists_domain.repository.WordListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ListDataModule
{
    @Provides
    fun provideTopListRepository() : TopListRepository {
        return TopListRepositoryImpl()
    }

    @Provides
    fun provideWordListRepository(wordsDao : WordsDao) : WordListRepository{
        return WordListRepositoryImpl(wordsDao)
    }
}