package com.example.lists_data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_bookmark")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val word : String,
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int
)