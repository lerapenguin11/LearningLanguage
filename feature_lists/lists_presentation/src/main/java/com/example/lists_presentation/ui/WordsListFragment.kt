package com.example.lists_presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.lists_presentation.databinding.FragmentWordsListBinding
import com.example.lists_presentation.ui.adapter.WordsListAdapter
import com.example.lists_presentation.viewmodel.WordListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordsListFragment : Fragment() {
    private var _binding : FragmentWordsListBinding? = null
    private val binding get() = _binding!!
    private var documentId: String? = null
    private val wordListViewModel : WordListViewModel by viewModels()
    private val adapter = WordsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            documentId = arguments?.getString(TITLE_TOP_LIST)
        }
        documentId?.let { wordListViewModel.getWordList(documentId = it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle()
        setWordsListRecyclerView()
        addAndDeleteWord()
    }

    private fun addAndDeleteWord() {
        adapter.bookmark = {
            wordListViewModel.bookmark(it)
        }
        adapter.unbookmark = {
            wordListViewModel.unbookmark(it)
        }
    }

    private fun setToolbarTitle() {
        if (documentId != null){
            binding.topAppBar.title = documentId
        } else{
            binding.topAppBar.title = CONST_TITLE
        }
    }

    private fun setWordsListRecyclerView() {
        wordListViewModel.wordList.observe(viewLifecycleOwner, Observer { wordLists ->
            adapter.submitList(wordLists)
            binding.linearProgressIndicator.visibility = View.GONE
        })
        binding.rvWordsList.adapter = adapter
    }


    companion object {
        const val TITLE_TOP_LIST = "title_top_list"
        const val CONST_TITLE = "Top words"
    }
}