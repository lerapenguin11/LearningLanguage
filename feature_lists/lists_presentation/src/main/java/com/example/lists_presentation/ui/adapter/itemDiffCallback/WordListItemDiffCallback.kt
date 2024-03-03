package com.example.lists_presentation.ui.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.lists_domain.entity.WordList
import com.example.lists_presentation.entity.WordsListWithStatus

class WordListItemDiffCallback : DiffUtil.ItemCallback<WordsListWithStatus>() {
    override fun areItemsTheSame(oldItem: WordsListWithStatus,
                                 newItem: WordsListWithStatus): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordsListWithStatus,
                                    newItem: WordsListWithStatus): Boolean {
        return oldItem.status == newItem.status
    }
}