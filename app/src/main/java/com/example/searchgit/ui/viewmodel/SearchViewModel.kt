package com.example.searchgit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.searchgit.data.GitUser
import com.example.searchgit.data.ResultStatus
import com.example.searchgit.repository.GitUserAPIRepository
import com.example.searchgit.repository.GitUserDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gitUserAPIRepo: GitUserAPIRepository,
    private val gitUserDBRepo: GitUserDBRepository
) : ViewModel() {
    val searchText = MutableLiveData<String>()

    // abstract
    // implements -> interface
    // 이렇게 선언하는 이유 :
    // 이 ViewModel에서는 비즈니스 로직을 수행하면서 _name변수를 변경할것이다.(_name.value = "??")
    // 그리고 외부에다가는 name 변수를 보여줄것이다. observe되는 것도 이 변수이다.
    // 외부에선 set은 할 수 없고 오로지 get(Observe)만 하도록 하기 위함이다.
    private val _gitUsers = MutableLiveData<ArrayList<GitUser>>()
    val gitUsers: LiveData<ArrayList<GitUser>> = _gitUsers

    init {
        searchText.value = "chance"
        // 위처럼 초기화를 해도 되지만, 아래처럼 선언할때도 가능
        //val searchText = MutableLiveData<String>("pparkjae")
    }

    //fun showGitUsers(gitUserId:String) = gitUserAPIRepo.getGitUsers(gitUserId)

    fun showGitUser1() = gitUserAPIRepo.getGitUsers(searchText.value)

    fun showGitUsers() {
        //viewModelScope.launch(Dispatchers.IO) {
          ///  val apiGitUsers:MutableSet<String> = mutableSetOf()

            //val resultGitUserDb = gitUserDBRepo.selectAll().value

            //Log.e("ccs","$resultGitUserDb")
//            for(i in resultGitUserDb){
//                apiGitUsers.add(i.id)
//            }
//
            //val resultGitUser =
            //Log.e("ccs","$resultGitUser")
//            for(i in resultGitUser){
//                //val setResult = apiGitUsers.add(i.id)
//                if(!apiGitUsers.add(i.id)){
//                    i.isLike = true
//                }
//            }

            //_gitUsers.postValue(resultGitUser)
        //}
    }

    fun changeLikeStatus(position: Int) = liveData {
        emit(ResultStatus.Loading)
        try {
            var gitUserDB = _gitUsers.value?.get(position)

            if(!gitUserDB?.isLike!!){
                gitUserDB?.isLike = true
                emit(ResultStatus.Success(gitUserDBRepo.insert(gitUserDB)))
            }else{
                gitUserDB?.isLike = false
                emit(ResultStatus.Success(gitUserDBRepo.deleteOne(gitUserDB.id)))
            }

        } catch (e: Exception) {
            emit(ResultStatus.Error(e))
        }
    }
}

//class SearchViewModelFactory(
//    private val gitUserAPIRepo: GitUserAPIRepository,
//    private val gitUserDBRepo: GitUserDBRepository
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
//            return SearchViewModel(gitUserAPIRepo, gitUserDBRepo) as T
//        }
//        throw IllegalArgumentException("Not Found ViewModel Class!")
//    }
//}


