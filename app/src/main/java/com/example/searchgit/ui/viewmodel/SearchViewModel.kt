package com.example.searchgit.ui.viewmodel

import android.util.Log
import androidx.core.app.JobIntentService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgit.model.GitUser
import com.example.searchgit.model.GitUsers
import com.example.searchgit.network.NetworkModule.service
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    val searchText = MutableLiveData<String>()


    // abstract
    // implements -> interface
    //
    private val _gitUsers = MutableLiveData<MutableList<GitUser>?>()
    val gitUsers : LiveData<MutableList<GitUser>?>
        get() = _gitUsers

    private var items: MutableList<GitUser> = mutableListOf()

    init {
//        items = arrayListOf(
//            GitUser("https://t1.daumcdn.net/cfile/tistory/2511E03B577BB58733","chancer4","https://api.github.com/users/CHANCEEWELL"),
//            GitUser("https://t1.daumcdn.net/cfile/tistory/2511E03B577BB58733","ChancePayne","https://api.github.com/users/CHANCEEWELL"),
//            GitUser("https://t1.daumcdn.net/cfile/   tistory/2511E03B577BB58733","chancegarcia","https://api.github.com/users/CHANCEEWELL")
//        )
       // _gitUsers.value = items
        searchText.value = "chance-up"
    }


    fun buttonClick(){
        //items.clear()
        //items.add(user)
        //_gitUsers.value = items



        viewModelScope.launch {
            val result = service.getGitUsers(searchText.value)
            Log.d("ccs", "onResponse성공 : $result.items")

            for(i in result.items) {
                this@SearchViewModel.items.add(i)
            }
            _gitUsers.value = items
        }
//        //val tempVal = launch
//        searchText.value?.let {
//            service.getGitUsers(it).enqueue(object : Callback<GitUsers> {
//                override fun onResponse(call: Call<GitUsers>, response: Response<GitUsers>) {
//                    val result = response.body()
//                    result?.run {
//
//                        Log.d("ccs", "onResponse성공 : ${toString()}")
//                        Log.d("ccs", "onResponse성공 : $items")
//                        //객체 하나만 쓸때는 중괄호 없어도댐
//
//                        for(i in items){
//                            Log.d("ccs", "onResponse성공 : $i")
//
//                            var user : GitUser = i
//                            i.run {
//                                Log.d("ccs", "Image:: $image")
//                                Log.d("ccs", "ID   :: $id")
//                                Log.d("ccs", "URL  :: $url")
//
//                            }
//                            //this@FirstViewModel.items.add(i)
//                        }
//                        _gitUsers.value = items.toMutableList()
//                    }
//
//                }
//
//                override fun onFailure(call: Call<GitUsers>, t: Throwable) {
//                    // 통신 실패 시
//                    Log.d("ccs","onFailure" + t.message.toString())
//                }
//            })
//        }

    }


}

