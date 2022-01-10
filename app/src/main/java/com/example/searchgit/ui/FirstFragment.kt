package com.example.searchgit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.searchgit.R
import com.example.searchgit.model.GitUsers
import com.example.searchgit.network.RetrofitClient
import com.example.searchgit.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RetrofitClient.apiService.getGitUsers("chance").enqueue(object :Callback<GitUsers>{
            override fun onResponse(call: Call<GitUsers>, response: Response<GitUsers>) {
                var result: GitUsers? = response.body()
                Log.d("ccs", "onResponse성공 : " + result?.toString())
            }

            override fun onFailure(call: Call<GitUsers>, t: Throwable) {
                // 통신 실패 시
                Log.d("ccs","onFailure" + t.message.toString())
            }
        })

//
//        val retrofit = RetrofitClient.getInstance()
//        Log.d("ccs","1111 : "+retrofit.toString())
//        val service = retrofit.create(RetrofitService::class.java)
//        Log.d("ccs","2222 : "+service.toString())
//
//        service.getGitUsers("chance").enqueue(object : Callback<GitUsers>{
//            override fun onResponse(call: Call<GitUsers>, response: Response<GitUsers>) {
//                if(response.isSuccessful) {
//                    //정상적으로 통신이 된 경우
//                    var result: GitUsers? = response.body()
//                    Log.d("ccs", "onResponse성공 : " + result?.toString())
//                }else{
//                    Log.d("ccs","onResponse실패 : " )
//                }
//            }
//
//            override fun onFailure(call: retrofit2.Call<GitUsers>, t: Throwable) {
//                // 통신 실패 시
//                Log.d("ccs","onFailure" + t.message.toString())
//            }
//
//        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


}