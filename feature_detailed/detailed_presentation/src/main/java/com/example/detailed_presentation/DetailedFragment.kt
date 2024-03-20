package com.example.detailed_presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.detailed_presentation.adapter.TranslationAdapter
import com.example.detailed_presentation.databinding.FragmentDetailedBinding
import com.example.detailed_presentation.viewmodel.WordDetailedViewModel
import com.example.word_domain.model.Word
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class DetailedFragment : Fragment() {
    private var _binding : FragmentDetailedBinding? = null
    private val binding get() = _binding!!
    private val detailedViewModel : WordDetailedViewModel by viewModels()
    private var  wordId : Int? = null
    private var wordIsWordList : Word? = null
    private val adapterTranslation = TranslationAdapter()

    //TODO поменять шрифт у title topAppBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wordId = it.getInt(TAG_WORD_ID)
            wordIsWordList = it.getParcelable(TAG_WORD)
        }
        wordId?.let { detailedViewModel.getWordDetailedId(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialToolbar.setTitleTextAppearance(requireContext(), R.style.TopAppBarTextAppearance)
    }

    override fun onResume() {
        super.onResume()
        //observeDataWordDetailed()
        setDataWordDetail(wordIsWordList)
    }

    private fun observeDataWordDetailed() {
        detailedViewModel.wordDetailed.observe(viewLifecycleOwner, Observer {word ->
            //setDataWordDetail(word)
        })
    }

    private fun setDataWordDetail(word: Word?) {
        binding.materialToolbar.setTitle(word?.word)
        binding.tvTranscription.text = word?.transcription
        binding.tvProgress.text = word?.progress.toString()
        setRecyclerViewTranslation(word?.translation)
    }

    private fun setRecyclerViewTranslation(translation : ArrayList<String>?) {
        adapterTranslation.submitList(translation)
        binding.rvTranslation.adapter = adapterTranslation
    }

    companion object {
        const val TAG_WORD_ID = "tag_word_id"
        const val TAG_WORD = "tag_word"
    }
}