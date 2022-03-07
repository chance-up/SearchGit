package com.example.searchgit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgit.data.GitUser
import com.example.searchgit.repository.GitUserDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val gitUserDBRepo: GitUserDBRepository):ViewModel() {
    private val _gitUsers = MutableLiveData<ArrayList<GitUser>>()
    val gitUsers : LiveData<ArrayList<GitUser>> = _gitUsers

    init {
        getList()
    }
    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            _gitUsers.postValue(ArrayList(gitUserDBRepo.selectAll()))
        }
    }

    fun changeStatus(position: Int) = gitUserDBRepo.changeLikeStatus(position,_gitUsers.value)
}

