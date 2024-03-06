package com.example.word_presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.word_presentation.adapter.WordsAdapter
import com.example.word_presentation.databinding.FragmentWordsBinding
import com.example.word_presentation.viewmodel.WordsViewModel
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
        checkWordsList()
        visibleAndGoneText()
    }

    private fun checkWordsList() {
        checkWordList = wordListViewModel.wordList.value != null &&
                wordListViewModel.wordList.value?.toList()?.size != 0
    }

    private fun setWordRecyclerView() {
        wordListViewModel.wordList.observe(viewLifecycleOwner, Observer { wordLists ->
            adapter.submitList(wordLists)
        })
        binding.rvWordsList.adapter = adapter
    }

    private fun visibleAndGoneText() {
        when(checkWordList){
            true -> binding.tvTextNoWord.visibility = View.GONE
            false -> binding.tvTextNoWord.visibility = View.VISIBLE
            else -> {
                Toast.makeText(requireContext(),
                    "Возникла проблема!", Toast.LENGTH_SHORT).show()}
        }
    }

    override fun onResume() {
        super.onResume()
        visibleAndGoneText()
        checkingList()
    }

    private fun checkingList() {
        if (checkWordList == true){
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