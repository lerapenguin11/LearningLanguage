package com.example.word_data.mappers

import com.example.lists_data.entities.WordEntity
import com.example.word_domain.model.Word

class WordListMapper
{
    fun toWordListIsEntity(wordEntity: WordEntity) : Word {
        return Word(
            id = wordEntity.id,
            word = wordEntity.word,
            transcription = wordEntity.transcription,
            translation = wordEntity.translation,
            notes = wordEntity.notes,
            progress = wordEntity.progress
        )
    }

    fun toWordEntity(word : Word): WordEntity {
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