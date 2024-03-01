package com.example.lists_domain.entity

data class WordList(
    val id : Int = 0,
    val word : String = "",
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int
)
