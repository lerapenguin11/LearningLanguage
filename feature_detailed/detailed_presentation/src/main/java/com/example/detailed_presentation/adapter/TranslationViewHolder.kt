package com.example.detailed_presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.detailed_presentation.R

class TranslationViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val translation : TextView = view.findViewById(R.id.tv_translations)
}