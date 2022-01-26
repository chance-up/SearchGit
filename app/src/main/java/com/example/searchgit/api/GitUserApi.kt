package com.example.searchgit.api

import com.example.searchgit.data.GitUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface GitUserApi {
    @GET("search/users")
    suspend fun getGitUsers(
        @Query("q") q: String?
    ): GitUsers
}