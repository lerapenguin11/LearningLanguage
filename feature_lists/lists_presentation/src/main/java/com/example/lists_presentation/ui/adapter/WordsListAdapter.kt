package com.example.lists_presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lists_domain.entity.TopList
import com.example.lists_domain.entity.WordList
import com.example.lists_presentation.R
import com.example.lists_presentation.entity.BookmarkStatus
import com.example.lists_presentation.entity.WordsListWithStatus
import com.example.lists_presentation.ui.adapter.itemDiffCallback.WordListItemDiffCallback
import com.example.lists_presentation.ui.adapter.viewHolder.WordListViewHolder

class WordsListAdapter: ListAdapter<WordsListWithStatus, WordListViewHolder>(
    WordListItemDiffCallback()
)
{
    var bookmark : ((WordsListWithStatus) -> Unit)? = null
    var unbookmark : ((WordsListWithStatus) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_DISABLE -> R.layout.item_word_not_fav
            VIEW_TYPE_ENABLE -> R.layout.item_word_fav
            else -> throw java.lang.RuntimeException("Unknow view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return WordListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val word = getItem(position)
        holder.word.text = word.word
        holder.translation.text = word.translation.toString().replace(
            Regex("[\\[\\]]"), "")

        when(word.status){
            BookmarkStatus.BOOKMARKED -> holder.btAddWord.setOnClickListener { unbookmark?.invoke(word) }
            BookmarkStatus.UNBOOKMARKED -> holder.btAddWord.setOnClickListener { bookmark?.invoke(word) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when(item.status){
            BookmarkStatus.BOOKMARKED -> VIEW_TYPE_ENABLE
            BookmarkStatus.UNBOOKMARKED -> VIEW_TYPE_DISABLE
        }
    }

    companion object{
        const val VIEW_TYPE_ENABLE = 100
        const val VIEW_TYPE_DISABLE = 101
    }
}