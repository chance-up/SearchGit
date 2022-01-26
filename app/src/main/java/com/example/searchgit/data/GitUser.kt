package com.example.searchgit.data

import com.google.gson.annotations.SerializedName

data class GitUsers(
    @SerializedName("total_count")
    val totalCount:String,
    @SerializedName("items")
    val items:ArrayList<GitUser>
)

data class GitUser(
    @SerializedName("avatar_url")
    val image:String,
    @SerializedName("login")
    val id:String,
    @SerializedName("html_url")
    val url:String
)