package com.example.detailed_domain.model

data class WordDetailed(
    val id : Int,
    val word : String,
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int
)
