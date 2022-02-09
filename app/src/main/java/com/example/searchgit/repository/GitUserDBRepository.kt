package com.example.searchgit.repository

import com.example.searchgit.data.GitUser
import com.example.searchgit.data.GitUserDao

class GitUserDBRepository(private val gitUserDao: GitUserDao) {

    fun selectOne(gitUserDbId: String) = gitUserDao.getGitUserDB(gitUserDbId)

    fun selectAll() = gitUserDao.getGitUsersDB()
    suspend fun insert(gitUser: GitUser?) = gitUserDao.insert(gitUser)
}