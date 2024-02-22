package com.example.lists_data.di

import com.example.lists_data.repository.TopListRepositoryImpl
import com.example.lists_domain.repository.TopListRepository
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
}