package com.example.detailed_presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.detailed_presentation.databinding.FragmentDetailedBinding
import com.example.detailed_presentation.view.DetailedView
import com.example.detailed_presentation.viewmodel.WordDetailedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class DetailedFragment : Fragment() {
    private var _binding : FragmentDetailedBinding? = null
    private val binding get() = _binding!!
    private val detailedViewModel : WordDetailedViewModel by viewModels()
    private var  wordId : Int? = null
    private var wordTitle : String? = null
    private var wordTranscription : String? = null
    private var wordProgress : Int? = null
    private var postponeUntilFunctions = mutableSetOf<suspend () -> Unit>()

    //TODO поменять шрифт у title topAppBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            wordId = it.getInt(TAG_WORD_ID)
            wordTitle = it.getString(TAG_WORD_TITLE)
            wordTranscription = it.getString(TAG_WORD_TRANSCRIPTION)
            wordProgress = it.getInt(TAG_WORD_PROGRESS)
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
        initDetailedView()
    }

    private fun initDetailedView() {
        val detailedView = DetailedView(FragmentDetailedBinding.bind(binding.root), object : DetailedView.Callback{
            override fun onReorderTrans(newTrans: List<String>) {

            }

            override fun onDeleteTrans(trans: String) {

            }

            override fun onListen() {

            }
        })
        setTextToolbar(detailedView)
        with(detailedViewModel){
            translation.observe(viewLifecycleOwner){trans ->
                trans?.let { detailedView.setTranslations(it) }
            }
            notes.observe(viewLifecycleOwner){note ->
                note?.let { detailedView.setNotes(it) }
            }
        }
        postponeUntil {
            detailedViewModel.translation.awaitValue()
            detailedViewModel.notes.awaitValue() }

    }

    private fun setTextToolbar(detailedView: DetailedView) {
        wordTitle?.let { detailedView.setTitleWord(it) }
        wordTranscription?.let { detailedView.setTranscription(it) }
        wordProgress?.let { detailedView.setProgress(it) }
    }


    companion object {
        const val TAG_WORD_ID = "tag_word_id"
        const val TAG_WORD_TITLE = "tag_word_title"
        const val TAG_WORD_TRANSCRIPTION = "tag_word_transcription"
        const val TAG_WORD_PROGRESS = "tag_word_progress"
    }

    interface Router {
        fun onWordDeleted(undo: suspend () -> Unit)
    }

    protected fun postponeUntil(await: suspend () -> Unit) {
        postponeUntilFunctions.add(await)
    }

    suspend fun LiveData<*>.awaitValue() {
        asFlow().first()
    }
}