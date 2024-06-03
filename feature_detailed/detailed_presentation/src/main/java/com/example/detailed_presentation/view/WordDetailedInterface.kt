package com.example.detailed_presentation.view

import androidx.lifecycle.LiveData
import com.example.detailed_domain.model.NotesDetailed
import com.example.detailed_domain.model.TranslationDetailed

interface WordDetailedInterface
{
    val translation : LiveData<List<TranslationDetailed>?>
    val notes : LiveData<List<NotesDetailed>?>
}