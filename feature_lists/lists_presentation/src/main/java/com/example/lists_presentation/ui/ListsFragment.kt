package com.example.lists_presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
    }

    private fun setTopListRecyclerView() {
        adapter = TopListAdapter()
        listViewModel.topList.observe(viewLifecycleOwner, Observer { topLists ->
            adapter.submitList(topLists)

        })
        binding.rvTopList.adapter = adapter
    }
}