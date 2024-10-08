package com.example.learninglanguage.di

import android.content.Context
import com.example.detailed_data.room.WordDetailedDao
import com.example.learninglanguage.room.AppDatabase
import com.example.lists_data.room.WordsDao
import com.example.word_data.room.WordsListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule
{
    @Provides
    @Singleton
    fun provideWordsDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideWordsDAO(appDatabase: AppDatabase): WordsDao {
        return appDatabase.getWordDao()
    }

    @Provides
    fun provideWordListDAO(appDatabase: AppDatabase): WordsListDao {
        return appDatabase.getWordsDao()
    }

    @Provides
    fun provideWordDetailedDAO(appDatabase: AppDatabase): WordDetailedDao {
        return appDatabase.getWordDetailed()
    }
}