package com.example.lists_data.mappers

import com.example.lists_data.entities.WordEntity
import com.example.lists_data.models.WordListModels
import com.example.lists_domain.entity.WordList
import kotlinx.coroutines.flow.StateFlow

class WordListMapper
{
    fun toWordList(wordList: StateFlow<List<WordListModels>>) : List<WordList>{
        val list = arrayListOf<WordList>()
        for (i in wordList.value){
            val model = WordList(
                id = i.id,
                word = i.word,
                translation = i.translation,
                transcription = i.transcription,
                notes = i.notes,
                progress =PROGRESS_START
            )
            list.add(model)
        }
        return list
    }

    fun toWordListIsEntity(wordEntity: WordEntity) : WordList {
        return WordList(
            id = wordEntity.id,
            word = wordEntity.word,
            transcription = wordEntity.transcription,
            translation = wordEntity.translation,
            notes = wordEntity.notes,
            progress = wordEntity.progress
        )
    }

    fun toWordEntity(word : WordList): WordEntity {
        return WordEntity(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = word.translation,
            notes = word.notes,
            progress = word.progress
        )
    }

    companion object{
        const val PROGRESS_START = 0
    }
}