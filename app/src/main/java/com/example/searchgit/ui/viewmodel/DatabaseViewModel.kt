package com.example.searchgit.ui.viewmodel

import androidx.lifecycle.*
import com.example.searchgit.data.GitUser
import com.example.searchgit.repository.GitUserDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DatabaseViewModel(private val gitUserDBRepo: GitUserDBRepository):ViewModel() {
    private val _gitUsers = MutableLiveData<ArrayList<GitUser>>()
    val gitUsers : LiveData<ArrayList<GitUser>> = _gitUsers

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _gitUsers.postValue(ArrayList(gitUserDBRepo.selectAll()))
        }

    }

}


class DatabaseViewModelFactory(
    private val gitUserDBRepo: GitUserDBRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DatabaseViewModel::class.java)){
            return DatabaseViewModel(gitUserDBRepo) as T
        }
        throw IllegalArgumentException("Not Found ViewModel Class!")
    }
}

