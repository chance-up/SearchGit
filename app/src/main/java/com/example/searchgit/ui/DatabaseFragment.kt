package com.example.searchgit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.searchgit.adapter.SearchRecyclerViewAdapter
import com.example.searchgit.data.AppDatabase
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.data.ResultStatus
import com.example.searchgit.databinding.FragmentDatabaseBinding
import com.example.searchgit.ui.viewmodel.DatabaseViewModel
import com.example.searchgit.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DatabaseFragment : Fragment() {
    private lateinit var databaseFragmentBinding: FragmentDatabaseBinding
    lateinit var gitUserDao: GitUserDao
    private val databaseViewModel: DatabaseViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    @Inject
    lateinit var searchRecyclerViewAdapter: SearchRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gitUserDao = AppDatabase.getInstance(requireContext()).gitUserDao()
        databaseFragmentBinding =
            FragmentDatabaseBinding.inflate(inflater, container, false).apply {
                vm = databaseViewModel
                lifecycleOwner = this@DatabaseFragment
            }

        sharedViewModel.update.observe(viewLifecycleOwner) {
            if (it) {
                databaseViewModel.getList()
            }
        }

        return databaseFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseFragmentBinding.githubUserView.adapter = searchRecyclerViewAdapter.apply {
            onClickLikeBtn = {
                lifecycleScope.launch {
                    changeLikeStatus(it)
                }
            }
        }
    }


    private fun changeLikeStatus(position:Int){
        databaseViewModel.changeStatus(position).observe(viewLifecycleOwner){result ->
            when (result) {
                ResultStatus.Loading -> {
                    Toast.makeText(
                        requireContext(),
                        "Database insert",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultStatus.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "실패했어요${result.throwable}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultStatus.Success -> {
                    if (result.data != 0) {
                        sharedViewModel.update.value = true
                    }
                }
            }
        }
    }


//    private fun showList(){
//        databaseViewModel.getList().observe(viewLifecycleOwner){ result ->
//            when(result){
//                is ResultStatus.Loading -> {
//                    Toast.makeText(
//                        requireContext(),
//                        "API Select All",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                is ResultStatus.Error -> {
//                    Toast.makeText(
//                        requireContext(),
//                        "실패했어요${result.throwable}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.e("ccs","${result.throwable}")
//                }
//                is ResultStatus.Success -> {
//                    if(result.data !=null){
//                        Log.e("ccs","${result.data}")
//                        databaseViewModel.receivedList(ArrayList(result.data))
//                    }
//                }
//            }
//        }
//    }
}