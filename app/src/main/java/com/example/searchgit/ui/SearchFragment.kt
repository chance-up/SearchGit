package com.example.searchgit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.searchgit.adapter.GitUserItemAdapter
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
        return searchFragmentBinding.root
    }
}