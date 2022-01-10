package com.example.searchgit.network

import retrofit2.Retrofit

import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
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