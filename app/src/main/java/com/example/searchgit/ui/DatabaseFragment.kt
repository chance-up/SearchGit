package com.example.searchgit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.searchgit.R
import com.example.searchgit.adapter.SearchRecyclerViewAdapter
import com.example.searchgit.data.DatabaseModule
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.databinding.FragmentDatabaseBinding
import com.example.searchgit.databinding.FragmentSearchBinding
import com.example.searchgit.repository.GitUserDBRepository
import com.example.searchgit.ui.viewmodel.DatabaseViewModel
import com.example.searchgit.ui.viewmodel.DatabaseViewModelFactory
import com.example.searchgit.ui.viewmodel.SharedViewModel

class DatabaseFragment : Fragment() {
    private lateinit var databaseFragmentBinding : FragmentDatabaseBinding
    lateinit var gitUserDao: GitUserDao
    private val databaseViewModel:DatabaseViewModel by viewModels { DatabaseViewModelFactory(
        GitUserDBRepository(gitUserDao)
    )}
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gitUserDao = DatabaseModule.getInstance(context!!).gitUserDao()
        databaseFragmentBinding = FragmentDatabaseBinding.inflate(inflater,container,false).apply {
            vm = databaseViewModel
            lifecycleOwner = this@DatabaseFragment
        }

        sharedViewModel.update.observe(viewLifecycleOwner, {
            if(it) {
                Log.e("ccs", "UPDATE")
                databaseViewModel.update()
            }
        })
        return databaseFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseFragmentBinding.githubUserView.adapter = SearchRecyclerViewAdapter()
    }
}