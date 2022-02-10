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
import androidx.navigation.navGraphViewModels
import com.example.searchgit.adapter.SearchRecyclerViewAdapter
import com.example.searchgit.data.DatabaseModule
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.data.ResultStatus
import com.example.searchgit.databinding.FragmentSearchBinding
import com.example.searchgit.repository.GitUserAPIRepository
import com.example.searchgit.repository.GitUserDBRepository
import com.example.searchgit.ui.viewmodel.SearchViewModel
import com.example.searchgit.ui.viewmodel.SearchViewModelFactory
import com.example.searchgit.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
    private lateinit var searchFragmentBinding : FragmentSearchBinding
    lateinit var gitUserDao:GitUserDao
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val searchViewModel: SearchViewModel by viewModels { SearchViewModelFactory(
        GitUserAPIRepository(),
        GitUserDBRepository(gitUserDao)
    ) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        gitUserDao = DatabaseModule.getInstance(context!!).gitUserDao()
        searchFragmentBinding = FragmentSearchBinding.inflate(inflater,container,false).apply {
            vm = searchViewModel
            lifecycleOwner = this@SearchFragment
        }

        return searchFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentBinding.githubUserView.adapter = SearchRecyclerViewAdapter().apply {
            onClickLikeBtn = {
                // position으로 get함.
                // 나중에 paging이 적용 될 경우에는?..?
                Log.e("onClickLikeBtn Frag ::","$it")
                Log.e("onClickLikeBtn Frag ::","${searchViewModel.gitUsers.value?.get(it)}")
                lifecycleScope.launch {
                    searchViewModel.changeLikeStatus(it).observe(viewLifecycleOwner) { result ->
                        when (result) {
                            ResultStatus.Loading -> {
                                Toast.makeText(requireContext(), "Database insert", Toast.LENGTH_SHORT).show()
                            }

                            is ResultStatus.Error -> {
                                Toast.makeText(requireContext(), "실패했어요${result.throwable}", Toast.LENGTH_SHORT).show()
                            }

                            is ResultStatus.Success -> {
                                if (result.data > 0) {
                                    sharedViewModel.update.value = true
                                }
                            }
                        }

                    }
                }
            }
            //getItemId()
        }

//        searchViewModel.gitUsers.observe(viewLifecycleOwner,{
//            Log.e("ccs5", "$it")
//            SearchRecyclerViewAdapter().submitList(it)
//        })

        //onClickLikeBtn



    }
}

