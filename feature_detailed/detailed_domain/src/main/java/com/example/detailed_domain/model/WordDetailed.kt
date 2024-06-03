package com.example.detailed_domain.model

data class WordDetailed(
    val id : Int,
    val word : String,
    val translation : List<TranslationDetailed>,
    val transcription : String,
    val notes : List<NotesDetailed>,
    val progress : Int
)

enum class StatusNotes {
    ACTIVE_ENTRY,
    INACTIVE_ENTRY
}

enum class TransferStatus {
    ACTIVE_ENTRY,
    INACTIVE_ENTRY
}

data class TranslationDetailed(
    val idTrans : Int,
    val trans : String,
    var transferStatus : TransferStatus = TransferStatus.ACTIVE_ENTRY
)

data class NotesDetailed(
    val idNotes : Int,
    val notes : String,
    var statusNotes : StatusNotes = StatusNotes.ACTIVE_ENTRY
)

