package com.example.lists_data.mappers

import com.example.lists_data.entities.Notes
import com.example.lists_data.entities.Translation
import com.example.lists_data.entities.WordEntity
import com.example.lists_data.models.WordListModels
import com.example.lists_domain.entity.WordList
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.ArrayList

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
            translation = getTranslationWord(wordEntity.translation),
            notes = getNotesWord(wordEntity.notes),
            progress = wordEntity.progress
        )
    }

    private fun getTranslationWord(transEntity: List<Translation>): ArrayList<String> {
        val trans = arrayListOf<String>()
        transEntity.forEach {
            trans.add(it.trans)
        }
        return trans
    }

    private fun getNotesWord(notesEntity: List<Notes>) : ArrayList<String>{
        val notes = arrayListOf<String>()
        notesEntity.forEach {
            notes.add(it.notes)
        }
        return notes
    }

    fun toWordEntity(word : WordList): WordEntity {
        return WordEntity(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = getTranslationEntity(word.translation),
            notes = getNotesEntity(word.notes),
            progress = word.progress
        )
    }

    fun toWordEntityDelete(word : WordList) : WordEntity{
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
        var count = -1
        translation.forEach {
            count++
            trans.add(Translation(trans = it, idTrans = count))

        }
        return trans
    }

    private fun getNotesEntity(notes: ArrayList<String>) : List<Notes>{
        var count = -1
        val notesList = arrayListOf<Notes>()
        notes.forEach {
            count++
            notesList.add(Notes(notes = it, idNotes = count))
        }
        return notesList
    }

    companion object{
        const val PROGRESS_START = 0
    }
}