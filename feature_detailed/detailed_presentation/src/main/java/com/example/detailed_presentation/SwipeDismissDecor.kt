package com.example.detailed_presentation

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.round

//TODO Вынести в отдельную фичу
class SwipeDismissDecor(
    private val itemBg: Drawable,
    private val onSwipe: (RecyclerView.ViewHolder) -> Unit
) : ItemTouchHelper(object : SimpleCallback(0, START or END) {

    override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = onSwipe(viewHolder)

    override fun onChildDraw(
        canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
    ) {
        if (dX != 0f) {
            val item = viewHolder.itemView
            val clipLeft = if (dX >= 0) 0 else item.width + dX.toInt()
            val clipRight = if (dX >= 0) dX.toInt() else item.width
            canvas.clipRect(clipLeft, item.top, clipRight, item.bottom)
            itemBg.setBounds(0, item.top, item.width, item.bottom)
            itemBg.alpha = round((1 - abs(dX / item.width)) * 255).toInt()
            itemBg.draw(canvas)
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.isActivated = false
        super.clearView(recyclerView, viewHolder)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState == ACTION_STATE_SWIPE) {
            viewHolder?.itemView?.isActivated = true
        }
        super.onSelectedChanged(viewHolder, actionState)
    }
})