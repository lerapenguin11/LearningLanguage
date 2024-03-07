package com.example.word_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.word_domain.model.WordsList
import com.example.word_presentation.adapter.WordsAdapter
import com.example.word_presentation.databinding.FragmentWordsBinding
import com.example.word_presentation.viewmodel.WordsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WordsFragment : Fragment() {
    private var _binding : FragmentWordsBinding? = null
    private val binding get() = _binding!!
    private val wordListViewModel : WordsViewModel by viewModels()
    private val adapter = WordsAdapter()
    private var checkWordList : Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            checkWordList = savedInstanceState.getBoolean(INTENT_BOOLEAN)
        }
        wordListViewModel.getWordList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerWordList()
    }

    private fun setupSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                wordListViewModel.deleteWord(item)
            }

        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvWordsList)
    }

    private fun observerWordList() {
        wordListViewModel.wordList.observe(viewLifecycleOwner, Observer { words ->
            checkWordsList(words)
            checkingList(words)
        })
    }

    private fun checkWordsList(words: List<WordsList>?) {
        if (!words.isNullOrEmpty()){
            checkWordList = true
            visibleAndGoneText()
        } else{
            checkWordList = false
            visibleAndGoneText()
        }
    }

    private fun setWordRecyclerView() {
        wordListViewModel.wordList.observe(viewLifecycleOwner, Observer {words ->
            adapter.submitList(words)
        })
        binding.rvWordsList.adapter = adapter
        setupSwipeListener()
    }

    private fun visibleAndGoneText() {
        when(checkWordList){
            true -> binding.tvTextNoWord.visibility = View.GONE
            false -> binding.tvTextNoWord.visibility = View.VISIBLE
            else -> {}
        }
    }

    override fun onResume() {
        super.onResume()
        visibleAndGoneText()
    }

    private fun checkingList(words: List<WordsList>?) {
        if (!words.isNullOrEmpty()){
            setWordRecyclerView()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        checkWordList?.let { outState.putBoolean(INTENT_BOOLEAN, it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvWordsList.adapter = null
        _binding = null
    }

    companion object {
        const val INTENT_BOOLEAN = "check"
    }
}