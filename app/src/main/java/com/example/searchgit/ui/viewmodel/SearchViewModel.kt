package com.example.searchgit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.searchgit.data.GitUser
import com.example.searchgit.repository.GitUserAPIRepository
import com.example.searchgit.repository.GitUserDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SearchViewModel(
    private val gitUserAPIRepo:GitUserAPIRepository,
    private val gitUserDBRepo:GitUserDBRepository
): ViewModel() {
    val searchText = MutableLiveData<String>()


    // abstract
    // implements -> interface
    // 이렇게 선언하는 이유 :
    // 이 ViewModel에서는 비즈니스 로직을 수행하면서 _name변수를 변경할것이다.(_name.value = "??")
    // 그리고 외부에다가는 name 변수를 보여줄것이다. observe되는 것도 이 변수이다.
    // 외부에선 set은 할 수 없고 오로지 get(Observe)만 하도록 하기 위함이다.
    private val _gitUsers = MutableLiveData<ArrayList<GitUser>>()
    val gitUsers : LiveData<ArrayList<GitUser>> = _gitUsers

    init {

        //_gitUsers.value = items
        searchText.value = "chance"
        // 위처럼 초기화를 해도 되지만,,
        // 선언 할 때
        //val searchText = MutableLiveData<String>("pparkjae")
        // 해도 된다.
    }

    fun showGitUsers(){
        viewModelScope.launch {
            val result = gitUserAPIRepo.getGitUsers(searchText.value)
            _gitUsers.value = result.items
        }
    }

    fun changeLikeStatus(position:Int){
        //Log.e("ccs","in ViewModel :: ${_gitUsers.value?.get(position)}")
        //Log.e("ccs","in ViewModel :: ${_gitUsers.value?.get(position)?.id}")
        //Log.e("ccs","in ViewModel :: ${_gitUsers.value?.get(position)}")
        //gitUserDBRepo.selectOne(_gitUsers.value?.get(position)?.id!!)



        viewModelScope.launch(Dispatchers.IO) {

            //Log.e("ccs","in ViewModel :: $result")


//
//            Log.e("ccs","in ViewModel :: $gitUserDB")
            val gitUserDB =_gitUsers.value?.get(position)
            val result = gitUserDBRepo.insert(gitUserDB)
            Log.e("ccs result","$result")
//            _gitUsers.value = (result.items)
//            val result1 = gitUserDBRepo.selectOne(_gitUsers.value?.get(position)?.id!!)
//            Log.e("ccs result1","$result1")
        }
    }
}

class SearchViewModelFactory(
    private val gitUserAPIRepo: GitUserAPIRepository,
    private val gitUserDBRepo:GitUserDBRepository
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(gitUserAPIRepo,gitUserDBRepo) as T
        }
        throw IllegalArgumentException("Not Found ViewModel Class!")
    }
}


