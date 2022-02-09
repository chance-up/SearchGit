package com.example.searchgit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GitUsers(
    @SerializedName("total_count")
    val totalCount:String,
    @SerializedName("items")
    val items:ArrayList<GitUser>
)

@Entity(tableName = "git_user_table")
data class GitUser(
    @SerializedName("avatar_url")
    @ColumnInfo(name="image")
    val image:String,
    @SerializedName("login")
    @PrimaryKey
    @ColumnInfo(name="id")
    val id:String,
    @SerializedName("html_url")
    @ColumnInfo(name="url")
    val url:String,
    @ColumnInfo(name="isLike")
    val isLike:Boolean = false
)


//data class GitUserDB(
//    @ColumnInfo(name="image")
//    val image:String,
//    @PrimaryKey
//    @ColumnInfo(name="id")
//    val id:String,
//    @ColumnInfo(name="url")
//    val url:String,
//    @ColumnInfo(name="isLike")
//    val isLike:Boolean
//)
