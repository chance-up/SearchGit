package com.example.searchgit.ui.viewmodel

import androidx.lifecycle.*
import com.example.searchgit.data.GitUser
import com.example.searchgit.network.NetworkModule.service
import com.example.searchgit.repository.GitUserRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SearchViewModel(private val gitUserRepo:GitUserRepository) : ViewModel() {
    val searchText = MutableLiveData<String>()


    // abstract
    // implements -> interface
    // 이렇게 선언하는 이유 :
    // 이 ViewModel에서는 비즈니스 로직을 수행하면서 _name변수를 변경할것이다.(_name.value = "??")
    // 그리고 외부에다가는 name 변수를 보여줄것이다. observe되는 것도 이 변수이다.
    // 외부에선 set은 할 수 없고 오로지 get(Observe)만 하도록 하기 위함이다.
    private val _gitUsers = MutableLiveData<ArrayList<GitUser>>()
    val gitUsers : LiveData<ArrayList<GitUser>> = _gitUsers


    private var items: ArrayList<GitUser> = arrayListOf()

    init {
        //_gitUsers.value = items
        searchText.value = "pparkjae"
        // 위처럼 초기화를 해도 되지만,,
        // 선언 할 때
        //val searchText = MutableLiveData<String>("pparkjae")
        // 해도 된다.
    }

    fun _getGitUsers(){
        viewModelScope.launch {
            val result = gitUserRepo.getGitUsers(searchText.value)
            _gitUsers.value = (result.items)
            //_gitUsers.value = (result.items)
        }
    }
}

class SearchViewModelFactory(private val gitUserRepo: GitUserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(gitUserRepo) as T
        }
        throw IllegalArgumentException("Not Found ViewModel Class!")
    }
}


