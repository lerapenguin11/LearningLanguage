package com.example.lists_presentation.ui.adapter.viewHolder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lists_presentation.R

class WordListViewHolder(view : View) : RecyclerView.ViewHolder(view)
{
    val word : TextView = view.findViewById(R.id.tv_title_item_list_word)
    val translation : TextView = view.findViewById(R.id.tv_desc_item_list_word)
    val btAddWord : Button = view.findViewById(R.id.bt_add_word)
    //val btDeleteWord : Button = view.findViewById(R.id.bt_delete_word)
}