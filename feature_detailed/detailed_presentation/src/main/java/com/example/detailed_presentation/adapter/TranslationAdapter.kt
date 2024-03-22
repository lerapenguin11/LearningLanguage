package com.example.detailed_presentation.adapter

import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.detailed_presentation.R
import com.example.detailed_presentation.adapter.itemDiffCallback.TranslationItemDiffCallback
import com.example.detailed_presentation.adapter.viewHolder.TranslationViewHolder
import java.util.Collections

internal class TranslationAdapter(private val onReorder: (List<String>) -> Unit) : ListAdapter<String, TranslationViewHolder>(
    TranslationItemDiffCallback()
) {

    private val dragDecor = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
    ) {

        private var prevList: List<String>? = null

        override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            currentList.toMutableList().apply {
                Collections.swap(this, vh.adapterPosition, target.adapterPosition)
                submitList(this)
            }
            return true
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            prevList?.let {
                if (prevList != currentList) {
                    onReorder(currentList)
                }
                prevList = null
            }
            viewHolder.itemView.isActivated = false
            super.clearView(recyclerView, viewHolder)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                prevList = currentList
                viewHolder?.itemView?.isActivated = true
            }
            super.onSelectedChanged(viewHolder, actionState)
        }

        override fun isLongPressDragEnabled() = false

        override fun onChildDraw(
            c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
            dx: Float, dy: Float, actionState: Int, isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(c, recyclerView, viewHolder, dx, dy, actionState, isCurrentlyActive)
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                viewHolder.itemView.apply {
                    val topLimit = 0f
                    val bottomLimit = (recyclerView.height - height).toFloat()
                    if (!recyclerView.canScrollVertically(-1) && y < topLimit) {
                        y = topLimit
                    } else if (!recyclerView.canScrollVertically(1) && y > bottomLimit) {
                        y = bottomLimit
                    }
                }
            }
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    })

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addItemDecoration(dragDecor)
        dragDecor.attachToRecyclerView(recyclerView)
    }

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