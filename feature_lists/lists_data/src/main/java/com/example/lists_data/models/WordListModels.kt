package com.example.lists_data.models

data class WordListModels(
    val id : Int = 0,
    val word : String = "",
    val translation : ArrayList<String> = ArrayList<String>(),
    val transcription : String = "",
    val notes : ArrayList<String> = ArrayList<String>()
)
