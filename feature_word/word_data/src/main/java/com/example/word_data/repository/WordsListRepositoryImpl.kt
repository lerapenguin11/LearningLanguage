package com.example.word_data.repository

import com.example.word_data.mappers.WordListMapper
import com.example.word_data.room.WordsListDao
import com.example.word_domain.model.Word
import com.example.word_domain.repository.WordsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WordsListRepositoryImpl(
    private val wordDao: WordsListDao)
    : WordsListRepository
{
    private val mapper: WordListMapper = WordListMapper()
    override suspend fun getWordList(): Flow<List<Word>> {
        val savedBooksFlow = wordDao.getSavedWord()
        return savedBooksFlow.map { list ->
            list.map { element ->
                mapper.toWordListIsEntity(element)
            }
        }
    }

    override suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(mapper.toWordEntity(word))
    }
}