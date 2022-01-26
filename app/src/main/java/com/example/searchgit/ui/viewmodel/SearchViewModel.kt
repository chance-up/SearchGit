package com.example.searchgit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgit.data.GitUser
import com.example.searchgit.network.NetworkModule.service
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
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

    fun buttonClick(){
        //this@SearchViewModel.items.clear()
        viewModelScope.launch {

            val result = service.getGitUsers(searchText.value)
            _gitUsers.value = (result.items)
//            for(i in result.items) {
//                this@SearchViewModel.items.add(i)
//            }
//            _gitUsers.value = this@SearchViewModel.items
        }
    }
}



