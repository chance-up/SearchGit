package com.example.searchgit.network

import androidx.lifecycle.LiveData
import com.example.searchgit.model.GitUsers
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search/users")
    fun getGitUsers(
        @Query("q") q:String
    ):Call<GitUsers>

    companion object RetrofitClient {
        private const val BASE_URL:String = "https://api.github.com/"

        private val retrofit:Retrofit.Builder by lazy{
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        }

        val apiService: RetrofitService by lazy{
            retrofit.build().create(RetrofitService::class.java)
        }
    }
}