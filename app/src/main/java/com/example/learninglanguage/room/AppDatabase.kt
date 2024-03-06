package com.example.learninglanguage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lists_data.entities.Converters
import com.example.lists_data.entities.WordEntity
import com.example.lists_data.room.WordsDao
import com.example.word_data.room.WordsListDao

@Database(entities = [WordEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_db2")
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getWordDao() : WordsDao

    abstract fun getWordsDao() : WordsListDao
}