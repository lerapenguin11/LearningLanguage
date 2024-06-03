package com.example.lists_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_bookmark")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val translation: List<Translation>,
    val transcription: String,
    val notes: List<Notes>,
    val progress: Int
)

enum class StatusNotesEntity {
    ACTIVE_ENTRY,
    INACTIVE_ENTRY
}

enum class TransferStatusEntity {
    ACTIVE_ENTRY,
    INACTIVE_ENTRY
}

data class Translation(
    @PrimaryKey(autoGenerate = true)
    val idTrans : Int? = null,
    val trans : String,
    var transferStatus : TransferStatusEntity = TransferStatusEntity.ACTIVE_ENTRY
)

data class Notes(
    @PrimaryKey(autoGenerate = true)
    val idNotes : Int? = null,
    val notes : String,
    var statusNotes : StatusNotesEntity = StatusNotesEntity.ACTIVE_ENTRY
)
