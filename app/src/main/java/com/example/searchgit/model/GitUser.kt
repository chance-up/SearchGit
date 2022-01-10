package com.example.searchgit.model

import com.google.gson.annotations.SerializedName

data class GitUsers(
    @SerializedName("total_count")
    val totalCount:String,
    @SerializedName("items")
    val items:List<GitUser>
)

data class GitUser(
    @SerializedName("avatar_url")
    val image:String,
    @SerializedName("login")
    val id:String,
    @SerializedName("url")
    val url:String
)