package com.example.word_domain.model

data class WordsList(
    val id : Int,
    val word : String,
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int
)
