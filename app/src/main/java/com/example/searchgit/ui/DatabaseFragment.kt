package com.example.searchgit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.searchgit.AdapterType
import com.example.searchgit.R
import com.example.searchgit.adapter.SearchRecyclerViewAdapter
import com.example.searchgit.data.AppDatabase
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.data.ResultStatus
import com.example.searchgit.databinding.FragmentDatabaseBinding
import com.example.searchgit.repository.GitUserDBRepository
import com.example.searchgit.ui.viewmodel.DatabaseViewModel
import com.example.searchgit.ui.viewmodel.DatabaseViewModelFactory
import com.example.searchgit.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DatabaseFragment : Fragment() {
    private lateinit var databaseFragmentBinding : FragmentDatabaseBinding
    lateinit var gitUserDao: GitUserDao
    private val databaseViewModel:DatabaseViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gitUserDao = AppDatabase.getInstance(requireContext()).gitUserDao()
        databaseFragmentBinding = FragmentDatabaseBinding.inflate(inflater,container,false).apply {
            vm = databaseViewModel
            lifecycleOwner = this@DatabaseFragment
        }

        sharedViewModel.update.observe(viewLifecycleOwner) {
            if (it) {
                databaseViewModel.update()
            }
        }
        return databaseFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseFragmentBinding.githubUserView.adapter = SearchRecyclerViewAdapter(AdapterType.LOCAL).apply {
            onClickLikeBtn = {
                //Log.e("ccs","$it")
                lifecycleScope.launch {
                    databaseViewModel.changeLikeStatus(it).observe(viewLifecycleOwner){ result->
                        when(result){
                            ResultStatus.Loading -> {
                                Toast.makeText(requireContext(), "Database Delete", Toast.LENGTH_SHORT).show()
                            }
                            is ResultStatus.Error -> {
                                Toast.makeText(requireContext(), "실패했어요${result.throwable}", Toast.LENGTH_SHORT).show()
                            }
                            is ResultStatus.Success -> {
                                if (result.data != 0) {
                                    sharedViewModel.update.value = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}