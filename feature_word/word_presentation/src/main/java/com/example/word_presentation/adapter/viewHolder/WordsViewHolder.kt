package com.example.word_presentation.adapter.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.word_presentation.R

class WordsViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val transcription : TextView = view.findViewById(R.id.tv_transcription)
    val word : TextView = view.findViewById(R.id.tv_word)
    val translation : TextView = view.findViewById(R.id.tv_translation)
    val notes : TextView = view.findViewById(R.id.tv_notes)
    val textProgress : TextView = view.findViewById(R.id.tv_progress)
    val progressPar : ProgressBar = view.findViewById(R.id.progress_bar)
    val doneProgress : ImageView = view.findViewById(R.id.ic_done_progress)
}