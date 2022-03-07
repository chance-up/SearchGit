package com.example.searchgit.repository

import androidx.lifecycle.liveData
import com.example.searchgit.api.GitUserApi
import com.example.searchgit.data.GitUsers
import com.example.searchgit.data.ResultStatus
import java.lang.Exception
import javax.inject.Inject

class GitUserAPIRepository @Inject constructor(private val api : GitUserApi) {
//    suspend fun getGitUsers(id:String?) : GitUsers{
//        return api.getGitUsers(id)
//    }

    fun getGitUsers(id:String?) = liveData {
        emit(ResultStatus.Loading)
        try{
            emit(ResultStatus.Success(api.getGitUsers(id)))
        } catch (e: Exception) {
            emit(ResultStatus.Error(e))
        }
    }
}