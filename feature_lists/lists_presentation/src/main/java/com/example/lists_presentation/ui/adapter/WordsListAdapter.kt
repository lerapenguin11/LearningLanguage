package com.example.lists_presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lists_domain.entity.WordList
import com.example.lists_presentation.R
import com.example.lists_presentation.ui.adapter.itemDiffCallback.WordListItemDiffCallback
import com.example.lists_presentation.ui.adapter.viewHolder.WordListViewHolder

class WordsListAdapter: ListAdapter<WordList, WordListViewHolder>(
    WordListItemDiffCallback()
)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word_not_fav, parent, false)
        return WordListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val word = getItem(position)
        holder.word.text = word.word
        holder.translation.text = word.translation.toString().replace(Regex("[\\[\\]]"), "")
    }
}