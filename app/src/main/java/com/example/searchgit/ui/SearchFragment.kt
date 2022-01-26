package com.example.searchgit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.searchgit.adapter.SearchRecyclerViewAdapter
import com.example.searchgit.databinding.FragmentSearchBinding
import com.example.searchgit.ui.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var searchFragmentBinding : FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchFragmentBinding = FragmentSearchBinding.inflate(inflater,container,false).apply {
            vm = searchViewModel
            lifecycleOwner = this@SearchFragment
        }
        Log.e("ccs", "onCreateView 1111")
        return searchFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentBinding.githubUserView.adapter = SearchRecyclerViewAdapter()
        //searchFragmentBinding.githubUserView.adapter = SearchRecyclerViewAdapter()

    }
}