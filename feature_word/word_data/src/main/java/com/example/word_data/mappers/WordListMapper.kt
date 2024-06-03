package com.example.word_data.mappers

import com.example.lists_data.entities.Notes
import com.example.lists_data.entities.Translation
import com.example.lists_data.entities.WordEntity
import com.example.word_domain.model.Word

class WordListMapper
{
    fun toWordListIsEntity(wordEntity: WordEntity) : Word {
        return Word(
            id = wordEntity.id,
            word = wordEntity.word,
            transcription = wordEntity.transcription,
            translation = getWordTranslation(wordEntity.translation),
            notes = getWordNotes(wordEntity.notes),
            progress = wordEntity.progress
        )
    }

    private fun getWordTranslation(translation: List<Translation>): ArrayList<String> {
        val transList = arrayListOf<String>()
        translation.forEach {
            transList.add(it.trans)
        }
        return transList
    }

    private fun getWordNotes(notes: List<Notes>): ArrayList<String>{
        val noteList = arrayListOf<String>()
        notes.forEach {
            noteList.add(it.notes)
        }
        return noteList
    }

    fun toWordEntity(word : Word): WordEntity {
        return WordEntity(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = getTranslationEntity(word.translation),
            notes = getNotesEntity(word.notes),
            progress = word.progress
        )
    }

    private fun getTranslationEntity(translation: ArrayList<String>) : List<Translation>{
        val trans = arrayListOf<Translation>()
        translation.forEach {
            trans.add(Translation(trans = it))
        }
        return trans
    }

    private fun getNotesEntity(notes: ArrayList<String>) : List<Notes>{
        val notesList = arrayListOf<Notes>()
        notes.forEach {
            notesList.add(Notes(notes = it))
        }
        return notesList
    }
}