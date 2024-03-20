package com.example.detailed_presentation.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.detailed_presentation.R

class NotesViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val note : TextView = view.findViewById(R.id.tv_notes)
}