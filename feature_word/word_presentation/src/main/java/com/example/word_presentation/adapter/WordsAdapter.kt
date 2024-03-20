package com.example.word_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.word_domain.model.Word
import com.example.word_presentation.R
import com.example.word_presentation.adapter.itemDiffCallback.WordsItemDiffCallback
import com.example.word_presentation.adapter.viewHolder.WordsViewHolder

class WordsAdapter() : ListAdapter<Word, WordsViewHolder>(
    WordsItemDiffCallback())
{
    var openDetails : ((Word) -> Unit)? = null

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

        holder.itemView.setOnClickListener {
            openDetails?.invoke(word)
        }
        /*
        val progressPar : ProgressBar = view.findViewById(R.id.progress_bar)
        val doneProgress :*/
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}