package com.example.detailed_presentation.adapter

import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.detailed_domain.model.TranslationDetailed
import com.example.detailed_presentation.R
import com.example.detailed_presentation.adapter.itemDiffCallback.TranslationItemDiffCallback
import com.example.detailed_presentation.adapter.viewHolder.TranslationViewHolder
import java.util.Collections

internal class TranslationAdapter(private val onReorder: (List<String>) -> Unit) : ListAdapter<TranslationDetailed, TranslationViewHolder>(
    TranslationItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_translations, parent, false)
        return TranslationViewHolder(view)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) {
        val translation = getItem(position)
        holder.translation.text = translation.trans
    }
}