package com.example.word_presentation.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.word_domain.model.WordsList
import com.example.word_presentation.R
import com.example.word_presentation.adapter.itemDiffCallback.WordsItemDiffCallback
import com.example.word_presentation.adapter.viewHolder.WordsViewHolder

class WordsAdapter() : ListAdapter<WordsList, WordsViewHolder>(
    WordsItemDiffCallback())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_words, parent, false)
        return WordsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val word = getItem(position)
        holder.transcription.text = word.transcription
        holder.word.text = word.word
        holder.translation.text = word.translation.toString().replace(
            Regex("[\\[\\]]"), "")
        holder.notes.text = word.notes.toString().replace(
            Regex("[\\[\\]]"), "")
        holder.textProgress.text = word.progress.toString()
        /*
        val progressPar : ProgressBar = view.findViewById(R.id.progress_bar)
        val doneProgress :*/
    }
}