package com.example.detailed_data.mapper

import com.example.detailed_domain.model.NotesDetailed
import com.example.detailed_domain.model.StatusNotes
import com.example.detailed_domain.model.TransferStatus
import com.example.detailed_domain.model.TranslationDetailed
import com.example.detailed_domain.model.WordDetailed
import com.example.lists_data.entities.Notes
import com.example.lists_data.entities.StatusNotesEntity
import com.example.lists_data.entities.TransferStatusEntity
import com.example.lists_data.entities.Translation
import com.example.lists_data.entities.WordEntity

class WordDetailedMapper
{
    fun fromEntityToWordDetailed(wordEntity: WordEntity) : WordDetailed {
        return WordDetailed(
            id = wordEntity.id,
            word = wordEntity.word,
            transcription = wordEntity.transcription,
            translation = getTranslationWord(wordEntity.translation),
            notes = getNotesWord(wordEntity.notes),
            progress = wordEntity.progress
        )
    }

    private fun getTranslationWord(transEntity: List<Translation>): List<TranslationDetailed> {
        val trans = arrayListOf<TranslationDetailed>()
        transEntity.forEach {
            trans.add(
                TranslationDetailed(
                trans = it.trans,
                    transferStatus = statusTransferWordEntityToWordDetailed(it.transferStatus.name),
                    idTrans = it.idTrans!!
            ))
        }
        return trans
    }

    private fun getNotesWord(notesEntity: List<Notes>) : List<NotesDetailed>{
        val notes = arrayListOf<NotesDetailed>()
        notesEntity.forEach {
            notes.add(
                NotesDetailed(
                notes = it.notes,
                    statusNotes = statusNotesWordEntityToWordDetailed(it.statusNotes.name),
                    idNotes = it.idNotes!!
            ))
        }
        return notes
    }

    fun fromWordDetailedToEntity(word : WordDetailed): WordEntity {
        return WordEntity(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = getTranslationEntity(word.translation),
            notes = getNotesEntity(word.notes),
            progress = word.progress
        )
    }

    private fun getTranslationEntity(translation: List<TranslationDetailed>) : List<Translation>{
        val trans = arrayListOf<Translation>()
        translation.forEach {
            trans.add(Translation(trans = it.trans,
                transferStatus = statusTransferWordDetailedToWordEntity(it.transferStatus.name)))
        }
        return trans
    }

    private fun getNotesEntity(notes: List<NotesDetailed>) : List<Notes>{
        val notesList = arrayListOf<Notes>()
        notes.forEach {
            notesList.add(Notes(notes = it.notes,
                statusNotes = statusNotesWordDetailedToWordEntity(it.statusNotes.name)))
        }
        return notesList
    }

    private fun statusNotesWordDetailedToWordEntity(name: String): StatusNotesEntity{
        return when(name){
            StatusNotesEntity.ACTIVE_ENTRY.name -> StatusNotesEntity.ACTIVE_ENTRY
            StatusNotesEntity.INACTIVE_ENTRY.name -> StatusNotesEntity.INACTIVE_ENTRY

            else -> {StatusNotesEntity.ACTIVE_ENTRY}
        }
    }

    private fun statusTransferWordDetailedToWordEntity(name: String): TransferStatusEntity {
        return when(name){
            TransferStatusEntity.ACTIVE_ENTRY.name -> TransferStatusEntity.ACTIVE_ENTRY
            TransferStatusEntity.INACTIVE_ENTRY.name -> TransferStatusEntity.INACTIVE_ENTRY

            else -> {TransferStatusEntity.ACTIVE_ENTRY}
        }
    }

    private fun statusNotesWordEntityToWordDetailed(name: String): StatusNotes{
        return when(name){
            StatusNotes.ACTIVE_ENTRY.name -> StatusNotes.ACTIVE_ENTRY
            StatusNotes.INACTIVE_ENTRY.name -> StatusNotes.INACTIVE_ENTRY
            else -> {StatusNotes.ACTIVE_ENTRY}
        }
    }

    private fun statusTransferWordEntityToWordDetailed(name: String) : TransferStatus{
        return when(name){
            TransferStatus.ACTIVE_ENTRY.name -> TransferStatus.ACTIVE_ENTRY
            TransferStatus.INACTIVE_ENTRY.name -> TransferStatus.INACTIVE_ENTRY
            else -> {TransferStatus.ACTIVE_ENTRY}
        }
    }
}