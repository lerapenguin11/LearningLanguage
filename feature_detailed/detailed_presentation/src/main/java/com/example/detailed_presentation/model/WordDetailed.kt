package com.example.detailed_presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordDetailed(
    val id : Int,
    val word : String,
    val translation : ArrayList<String>,
    val transcription : String,
    val notes : ArrayList<String>,
    val progress : Int
) : Parcelable

//TODO удалить