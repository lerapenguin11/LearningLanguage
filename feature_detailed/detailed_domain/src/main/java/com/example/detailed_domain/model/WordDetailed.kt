package com.example.detailed_domain.model

data class WordDetailed(
    val id : Int,
    val word : String,
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int,
    val transferStatus : TransferStatus,
    val statusNotes : StatusNotes
)

enum class StatusNotes {
    ACTIVE_ENTRY,
    INACTIVE_ENTRY
}

enum class TransferStatus {
    ACTIVE_ENTRY,
    INACTIVE_ENTRY
}
