package com.example.searchgit.repository

import com.example.searchgit.data.GitUsers
import com.example.searchgit.network.NetworkModule

class GitUserAPIRepository {
    suspend fun getGitUsers(id:String?) : GitUsers{
        return NetworkModule.service.getGitUsers(id)
    }
}