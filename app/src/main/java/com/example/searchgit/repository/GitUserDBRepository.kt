package com.example.searchgit.repository

import androidx.lifecycle.liveData
import com.example.searchgit.data.GitUser
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.data.ResultStatus
import java.lang.Exception
import javax.inject.Inject

class GitUserDBRepository @Inject constructor(private val gitUserDao: GitUserDao) {
    fun selectAll() = gitUserDao.getGitUsersDB()
    suspend fun insert(gitUser: GitUser?) = gitUserDao.insert(gitUser)

    fun deleteOne(gitUserId: String) = liveData {
        emit(ResultStatus.Loading)
        try {
            val result = gitUserDao.deleteUserDB(gitUserId)
            emit(ResultStatus.Success(result))
        } catch (e: Exception) {
            emit(ResultStatus.Error(e))
        }
    }
}