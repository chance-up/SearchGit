package com.example.searchgit.network

import com.example.searchgit.model.GitUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search/users")
    fun getGitUsers(
        @Query("q") q:String
    ):Call<GitUsers>
}