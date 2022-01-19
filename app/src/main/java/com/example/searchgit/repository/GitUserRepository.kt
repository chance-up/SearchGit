package com.example.searchgit.repository

import com.example.searchgit.model.GitUsers
import com.example.searchgit.network.NetworkModule

class GitUserRepository {
    
    suspend fun getGitUsers(id:String) : GitUsers{
        return NetworkModule.service.getGitUsers(id)
    }
}