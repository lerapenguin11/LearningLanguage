package com.example.detailed_presentation.view

import androidx.lifecycle.LiveData

interface WordDetailedInterface
{
    val translation : LiveData<ArrayList<String>?>
    val notes : LiveData<ArrayList<String>?>
}