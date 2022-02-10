package com.example.searchgit.repository

import com.example.searchgit.api.GitUserApi
import com.example.searchgit.data.GitUsers
import javax.inject.Inject

class GitUserAPIRepository @Inject constructor(private val api : GitUserApi) {
    suspend fun getGitUsers(id:String?) : GitUsers{
        return api.getGitUsers(id)
    }
}