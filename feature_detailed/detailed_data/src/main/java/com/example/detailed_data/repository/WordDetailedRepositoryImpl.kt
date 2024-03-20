package com.example.detailed_data.repository

import com.example.detailed_data.mapper.WordDetailedMapper
import com.example.detailed_data.room.WordDetailedDao
import com.example.detailed_domain.model.WordDetailed
import com.example.detailed_domain.repository.WordDetailedRepository

class WordDetailedRepositoryImpl(
    private val dao: WordDetailedDao,
    private val mapper : WordDetailedMapper
) : WordDetailedRepository
{
    override suspend fun getWord(wordId: Int): WordDetailed {
        return mapper.fromEntityToWordDetailed(dao.getWordDetailed(wordId = wordId))
    }

    override suspend fun deleteWord(word: WordDetailed) {
        dao.deleteWordDetailed(mapper.fromWordDetailedToEntity(word = word))
    }

    override suspend fun updateWord(word: WordDetailed) {
        dao.updateWordDetailed(mapper.fromWordDetailedToEntity(word = word))
    }
}