package com.example.word_presentation.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.word_domain.model.Word

class WordsItemDiffCallback : DiffUtil.ItemCallback<Word>()
{
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }
}