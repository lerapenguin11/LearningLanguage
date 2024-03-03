package com.example.lists_presentation.entity

data class WordsListWithStatus(
    val id : Int = 0,
    val word : String = "",
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int,
    val status: BookmarkStatus
)
