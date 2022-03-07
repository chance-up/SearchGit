package com.example.searchgit.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.example.searchgit.data.GitUser
import com.example.searchgit.data.GitUserDao
import com.example.searchgit.data.GitUsers
import com.example.searchgit.data.ResultStatus
import java.lang.Exception
import javax.inject.Inject

class GitUserDBRepository @Inject constructor(private val gitUserDao: GitUserDao) {

    fun selectAll() = gitUserDao.getGitUsersDB()

//    fun selectAll() = liveData {
//        emit(ResultStatus.Loading)
//        try{
//            emit(ResultStatus.Success(gitUserDao.getGitUsersDB()))
//        } catch (e: Exception) {
//            emit(ResultStatus.Error(e))
//        }
//    }


    //suspend fun insert(gitUser: GitUser?) = gitUserDao.insert(gitUser)

//    fun deleteOne(gitUserId: String) = liveData {
//        emit(ResultStatus.Loading)
//        try {
//            val result = gitUserDao.deleteUserDB(gitUserId)
//            emit(ResultStatus.Success(result))
//        } catch (e: Exception) {
//            emit(ResultStatus.Error(e))
//        }
//    }


    fun changeLikeStatus(position:Int, gitUsers : ArrayList<GitUser>?) = liveData {
        emit(ResultStatus.Loading)
        try {
            var gitUserDB = gitUsers?.get(position)
            if(!gitUserDB?.isLike!!){
                gitUserDB?.isLike = true
                emit(ResultStatus.Success(gitUserDao.insert(gitUserDB)))

            }else{
                gitUserDB?.isLike = false
                emit(ResultStatus.Success(gitUserDao.deleteUserDB(gitUserDB.id)))
            }
        } catch (e: Exception) {
            emit(ResultStatus.Error(e))
        }
    }

    fun filterSearchResult(gitUsersAPI:ArrayList<GitUser>):ArrayList<GitUser>{
        val apiGitUsers:MutableSet<String> = mutableSetOf()

        val gitUsersDB:ArrayList<GitUser> = ArrayList(selectAll())

        for(i in gitUsersDB){
            apiGitUsers.add(i.id)
        }

        for(i in gitUsersAPI){
            if(!apiGitUsers.add(i.id)){
                i.isLike = true
            }
        }
        return gitUsersAPI
    }
}