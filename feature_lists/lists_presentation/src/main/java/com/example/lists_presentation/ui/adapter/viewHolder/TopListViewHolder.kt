package com.example.lists_presentation.ui.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lists_presentation.R

class TopListViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val title : TextView = view.findViewById(R.id.tv_title_item_list)
    val newWords : TextView = view.findViewById(R.id.tv_desc_item_list)
}