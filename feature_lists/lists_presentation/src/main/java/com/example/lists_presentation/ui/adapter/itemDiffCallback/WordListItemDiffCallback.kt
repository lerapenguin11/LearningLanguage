package com.example.lists_presentation.ui.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.lists_domain.entity.WordList

class WordListItemDiffCallback : DiffUtil.ItemCallback<WordList>() {
    override fun areItemsTheSame(oldItem: WordList, newItem: WordList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordList, newItem: WordList): Boolean {
        return oldItem == newItem
    }
}