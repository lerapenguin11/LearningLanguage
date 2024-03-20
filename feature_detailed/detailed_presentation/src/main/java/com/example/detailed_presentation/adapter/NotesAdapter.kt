package com.example.detailed_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.detailed_presentation.R
import com.example.detailed_presentation.adapter.itemDiffCallback.NotesItemDiffCallback
import com.example.detailed_presentation.adapter.viewHolder.NotesViewHolder

class NotesAdapter : ListAdapter<String, NotesViewHolder>(
    NotesItemDiffCallback()
)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.item_notes, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.note.text = note
    }
}