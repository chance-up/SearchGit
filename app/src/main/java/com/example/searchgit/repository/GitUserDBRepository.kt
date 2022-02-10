package com.example.searchgit.repository

import com.example.searchgit.data.GitUser
import com.example.searchgit.data.GitUserDao
import javax.inject.Inject

class GitUserDBRepository @Inject constructor(private val gitUserDao: GitUserDao) {
    fun selectAll() = gitUserDao.getGitUsersDB()
    suspend fun insert(gitUser: GitUser?) = gitUserDao.insert(gitUser)
    suspend fun deleteOne(gitUserDbId: String?) = gitUserDao.deleteUserDB(gitUserDbId)
}