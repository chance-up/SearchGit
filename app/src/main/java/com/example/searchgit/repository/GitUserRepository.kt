package com.example.searchgit.repository

import androidx.lifecycle.viewModelScope
import com.example.searchgit.data.GitUsers
import com.example.searchgit.network.NetworkModule
import kotlinx.coroutines.launch

class GitUserRepository {
    suspend fun getGitUsers(id:String?) : GitUsers{
        return NetworkModule.service.getGitUsers(id)
    }
}