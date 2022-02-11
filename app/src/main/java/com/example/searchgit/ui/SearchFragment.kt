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
import com.example.searchgit.databinding.FragmentSearchBinding
import com.example.searchgit.ui.viewmodel.SearchViewModel
import com.example.searchgit.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var searchFragmentBinding: FragmentSearchBinding
    lateinit var gitUserDao: GitUserDao
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val searchViewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var searchRecyclerViewAdapter: SearchRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gitUserDao = AppDatabase.getInstance(requireContext()).gitUserDao()
        searchFragmentBinding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            vm = searchViewModel
            lifecycleOwner = this@SearchFragment
        }
        searchFragmentBinding.searchButton.setOnClickListener {
            showList()
            //Log.e("ccs","ccccc")
        }


        return searchFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentBinding.githubUserView.adapter = searchRecyclerViewAdapter.apply {
            onClickLikeBtn = {
                // position으로 get함.
                // 나중에 paging이 적용 될 경우에는?..?
                lifecycleScope.launch {
                    // 'Search Fragment'에서
                    // Button 을 눌렀을 때, 이를 관찷(observe)하여 sharedViewmodel의 update라는 변수의 값을 true로 바꾸어준다.
                    // 'Database Fragment' 에서는
                    // sharedViewmodel의 update변수 값을 관찷(observe)하고 있다가, true로 바뀐다면 List를 새로 뿌려준다.
                    // 이렇게 sharedViewModel을 중간에 하나 둔 이유는, Fragment와 ViewModel이 1:1이면 좋기 때문..
                    searchViewModel.changeLikeStatus(it).observe(viewLifecycleOwner) { result ->
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
            }
        }

//        searchFragmentBinding.searchButton.setOnClickListener {
//            lifecycleScope.launch{
//                searchViewModel.showGitUsers()
////                searchViewModel.showGitUsers().observe(viewLifecycleOwner){ result->
////                    when (result) {
////                        ResultStatus.Loading -> {
////                            Toast.makeText(
////                                requireContext(),
////                                "API Search",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////
////                        is ResultStatus.Error -> {
////                            Toast.makeText(
////                                requireContext(),
////                                "실패했어요${result.throwable}",
////                                Toast.LENGTH_SHORT
////                            ).show()
////                        }
////
////                        is ResultStatus.Success -> {
////                            if (result.data != null) {
////                                searchViewModel.
////                            }
////                        }
////                    }
////                }
//            }
//        }





    }

    private fun showList(){
        searchViewModel.showGitUser1().observe(viewLifecycleOwner){ result ->
            when(result){
                is ResultStatus.Loading -> {
                    Toast.makeText(
                        requireContext(),
                        "API Select All",
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
                    if(result.data !=null){

                    }
                }
            }
            //Log.e("ccs","$it")

        }
    }
}

