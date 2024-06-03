package com.example.detailed_presentation.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.detailed_domain.model.NotesDetailed

class NotesItemDiffCallback : DiffUtil.ItemCallback<NotesDetailed>() {
    override fun areItemsTheSame(oldItem: NotesDetailed, newItem: NotesDetailed): Boolean {
        return oldItem.idNotes == newItem.idNotes
    }

    override fun areContentsTheSame(oldItem: NotesDetailed, newItem: NotesDetailed): Boolean {
        return oldItem == newItem
    }
}