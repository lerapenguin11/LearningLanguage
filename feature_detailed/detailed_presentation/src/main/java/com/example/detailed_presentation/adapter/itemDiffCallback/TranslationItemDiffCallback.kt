package com.example.detailed_presentation.adapter.itemDiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.detailed_domain.model.TranslationDetailed

class TranslationItemDiffCallback : DiffUtil.ItemCallback<TranslationDetailed>() {
    override fun areItemsTheSame(oldItem: TranslationDetailed, newItem: TranslationDetailed): Boolean {
        return oldItem.idTrans == newItem.idTrans
    }

    override fun areContentsTheSame(oldItem: TranslationDetailed, newItem: TranslationDetailed): Boolean {
        return oldItem == newItem
    }
}