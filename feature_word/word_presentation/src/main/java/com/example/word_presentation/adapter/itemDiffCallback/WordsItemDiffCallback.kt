package com.example.word_presentation.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.word_domain.model.WordsList

class WordsItemDiffCallback : DiffUtil.ItemCallback<WordsList>()
{
    override fun areItemsTheSame(oldItem: WordsList, newItem: WordsList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordsList, newItem: WordsList): Boolean {
        return oldItem == newItem
    }
}