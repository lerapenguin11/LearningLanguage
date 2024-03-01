package com.example.lists_presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lists_domain.entity.TopList
import com.example.lists_presentation.R
import com.example.lists_presentation.ui.adapter.itemDiffCallback.TopListItemDiffCallback
import com.example.lists_presentation.ui.adapter.viewHolder.TopListViewHolder

class TopListAdapter : ListAdapter<TopList, TopListViewHolder>(
    TopListItemDiffCallback())
{
    var onWordListClickListener : ((TopList) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,
            parent, false)
        return TopListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TopListViewHolder, position: Int) {
        val top = getItem(position)
        holder.title.text = top.title
        holder.newWords.text = "${new_words} ${top.new_words}"
        holder.itemView.setOnClickListener {
            onWordListClickListener?.invoke(top)
        }
    }

    companion object{
        const val new_words = "Новые слова:"
    }
}