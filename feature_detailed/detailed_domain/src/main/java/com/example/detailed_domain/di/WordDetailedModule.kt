package com.example.detailed_domain.di

import com.example.detailed_domain.repository.WordDetailedRepository
import com.example.detailed_domain.usecase.DeleteWordDetailedUseCase
import com.example.detailed_domain.usecase.GetWordDetailedUseCase
import com.example.detailed_domain.usecase.UpdateWordDetailedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object WordDetailedModule
{
    @Provides
    fun provideGetWordDetailedUseCase(repository : WordDetailedRepository) :
            GetWordDetailedUseCase {
        return GetWordDetailedUseCase(repository)
    }

    @Provides
    fun provideDeleteWordDetailedUseCase(repository : WordDetailedRepository) :
            DeleteWordDetailedUseCase {
        return DeleteWordDetailedUseCase(repository)
    }

    @Provides
    fun provideDeleteWordUseCase(repository : WordDetailedRepository) :
            UpdateWordDetailedUseCase {
        return UpdateWordDetailedUseCase(repository)
    }
}