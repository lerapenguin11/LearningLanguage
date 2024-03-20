package com.example.detailed_data.di

import com.example.detailed_data.mapper.WordDetailedMapper
import com.example.detailed_data.repository.WordDetailedRepositoryImpl
import com.example.detailed_data.room.WordDetailedDao
import com.example.detailed_domain.repository.WordDetailedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object WordDetailedModule
{
    @Provides
    fun provideWordDetailedMapper() : WordDetailedMapper{
        return WordDetailedMapper()
    }

    @Provides
    fun provideWordDetailedRepository(dao : WordDetailedDao, mapper: WordDetailedMapper):
            WordDetailedRepository{
        return WordDetailedRepositoryImpl(dao, mapper)
    }
}