package com.example.searchgit.data

import androidx.room.*

@Dao
interface GitUserDao {

    @Query("SELECT * FROM git_user_table WHERE id=:gitUserId")
    fun getGitUserDB(gitUserId: String): GitUser

    @Query("SELECT * FROM git_user_table ORDER BY id ASC")
    fun getGitUsersDB(): List<GitUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gitUser: GitUser?): Long

    @Update
    suspend fun update(gitUser: GitUser)

    @Delete
    suspend fun delete(gitUser: GitUser)
}