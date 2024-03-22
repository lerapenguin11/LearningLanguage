package com.example.detailed_presentation.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.detailed_presentation.R
import com.example.detailed_presentation.SwipeDismissDecor
import com.example.detailed_presentation.adapter.NotesAdapter
import com.example.detailed_presentation.adapter.TranslationAdapter
import com.example.detailed_presentation.databinding.FragmentDetailedBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.Integer.getInteger

internal class DetailedView(
    private val binding : FragmentDetailedBinding,
    private val callback: Callback
)
{
    interface Callback {
        //fun onDelete()
        fun onReorderTrans(newTrans: List<String>)
        //fun onAddTrans()
        //fun onEditTrans(trans: String)
        fun onDeleteTrans(trans: String)
        fun onListen()
    }

    private val adapterTranslation = TranslationAdapter(callback::onReorderTrans)
    private val adapterNotes = NotesAdapter()
    private var shortAnimationDuration: Int = 0

    init {
        binding.rvTranslation.apply {
            adapter = adapterTranslation
            layoutManager = LinearLayoutManager(context)
            val swipeDecor =
                SwipeDismissDecor(AppCompatResources.getDrawable(context, R.drawable.ic_delete)!!) {
                    callback.onDeleteTrans(adapterTranslation.currentList[it.adapterPosition])
                }
            addItemDecoration(swipeDecor.also { it.attachToRecyclerView(this) })

        }
    }

    fun showDeleteTransUndo(undo: () -> Unit) = with(binding) {
        Snackbar.make(root, R.string.text_translation_deleted, Snackbar.LENGTH_LONG)
            .setAction(R.string.text_undo) { undo() }
            .show()
    }

    fun setTitleWord(text: String) = with(binding) {
        materialToolbar.title = text
    }

    fun setTranscription(text: String) = with(binding) {
        tvTranscription.text = text
    }

    fun setProgress(progress : Int) = with(binding) {
        tvProgress.text = progress.toString()
    }

    fun setTranslations(trans: List<String>?) = with(binding) {
        if (trans != null) {
            adapterTranslation.submitList(trans)
        }
    }

    fun setNotes(notes: List<String>?) = with(binding) {
        if (notes != null) {
            adapterNotes.submitList(notes)
            rvNotes.adapter = adapterNotes
            crossfade()
        }
    }

    private fun crossfade() = with(binding) {
        shortAnimationDuration = 200
        linearProgressIndicator.animate()
            .alpha(0f)
            .setDuration(shortAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    linearProgressIndicator.visibility = View.GONE
                    containerContent.apply {
                        alpha = 0f
                        visibility = View.VISIBLE
                        animate()
                            .alpha(1f)
                            .setDuration(shortAnimationDuration.toLong())
                            .setListener(null)
                    }
                }
            })
    }
}