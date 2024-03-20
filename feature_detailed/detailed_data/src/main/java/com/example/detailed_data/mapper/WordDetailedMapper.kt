package com.example.detailed_data.mapper

import com.example.detailed_domain.model.WordDetailed
import com.example.lists_data.entities.WordEntity

class WordDetailedMapper
{
    fun fromEntityToWordDetailed(wordEntity: WordEntity) : WordDetailed {
        return WordDetailed(
            id = wordEntity.id,
            word = wordEntity.word,
            transcription = wordEntity.transcription,
            translation = wordEntity.translation,
            notes = wordEntity.notes,
            progress = wordEntity.progress
        )
    }

    fun fromWordDetailedToEntity(word : WordDetailed): WordEntity {
        return WordEntity(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = word.translation,
            notes = word.notes,
            progress = word.progress
        )
    }
}