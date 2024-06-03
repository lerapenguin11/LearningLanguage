package com.example.detailed_data.mapper

import com.example.detailed_domain.model.StatusNotes
import com.example.detailed_domain.model.TransferStatus
import com.example.detailed_domain.model.WordDetailed
import com.example.lists_data.entities.StatusNotesEntity
import com.example.lists_data.entities.TransferStatusEntity
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
            progress = wordEntity.progress,
            statusNotes = statusNotesWordEntityToWordDetailed(wordEntity.statusNotes.name),
            transferStatus =statusTransferWordEntityToWordDetailed(wordEntity.transferStatus.name)
        )
    }

    fun fromWordDetailedToEntity(word : WordDetailed): WordEntity {
        return WordEntity(
            id = word.id,
            word = word.word,
            transcription = word.transcription,
            translation = word.translation,
            notes = word.notes,
            progress = word.progress,
            statusNotes = statusNotesWordDetailedToWordEntity(word.statusNotes.name),
            transferStatus = statusTransferWordDetailedToWordEntity(word.transferStatus.name)
        )
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