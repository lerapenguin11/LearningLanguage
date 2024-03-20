package com.example.detailed_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.detailed_presentation.R

class TranslationAdapter : ListAdapter<String, TranslationViewHolder>(
    TranslationItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_translations, parent, false)
        return TranslationViewHolder(view)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        val translation = getItem(position)
        holder.translation.text = translation
    }
}