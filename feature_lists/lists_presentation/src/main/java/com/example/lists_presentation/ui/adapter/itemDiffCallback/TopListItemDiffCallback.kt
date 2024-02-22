package com.example.lists_presentation.ui.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.lists_domain.entity.TopList

class TopListItemDiffCallback : DiffUtil.ItemCallback<TopList>() {
    override fun areItemsTheSame(oldItem: TopList, newItem: TopList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TopList, newItem: TopList): Boolean {
        return oldItem == newItem
    }
}