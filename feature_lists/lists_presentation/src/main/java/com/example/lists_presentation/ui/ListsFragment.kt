package com.example.lists_presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.lists_presentation.R
import com.example.lists_presentation.databinding.FragmentListsBinding
import com.example.lists_presentation.ui.adapter.TopListAdapter
import com.example.lists_presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListsFragment : Fragment() {
    private var _binding : FragmentListsBinding? = null
    private val binding get() = _binding!!
    private val listViewModel : ListViewModel by viewModels()
    private lateinit var adapter : TopListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTopListRecyclerView()
        setOnClickListenerItemList()
    }

    private fun setOnClickListenerItemList() {
        adapter.onWordListClickListener = {
            newInstanceWordsListFragment(it.title)
        }
    }

    private fun newInstanceWordsListFragment(titleList : String){
        if (titleList.isNotEmpty()){
            val bundle = Bundle()
            bundle.putString(TITLE_TOP_LIST, titleList)
            view?.findNavController()?.navigate(R.id.action_listsFragment_to_wordsListFragment, bundle)
        }
    }

    private fun setTopListRecyclerView() {
        adapter = TopListAdapter()
        listViewModel.topList.observe(viewLifecycleOwner, Observer { topLists ->
            adapter.submitList(topLists)
            binding.linearProgressIndicator.visibility = View.GONE

        })
        binding.rvTopList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvTopList.adapter = null
        _binding = null
    }

    companion object{
        const val TITLE_TOP_LIST = "title_top_list"
    }
}