package com.example.searchgit.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.R
import com.example.searchgit.model.GitUser

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun setItem(recyclerView: RecyclerView, items:MutableList<GitUser>?){
        if(items == null){
            return
        }
        if(recyclerView.adapter ==null){
            recyclerView.adapter = GitUserItemAdapter()
        }

        val gitUserAdapter = recyclerView.adapter as GitUserItemAdapter
        gitUserAdapter.gitUsers = items!!
        gitUserAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(imageUrl : String){
        if(imageUrl == null){
            return
        }

        Glide.with(context)
            .load(imageUrl)
            .error(com.example.searchgit.R.drawable.ic_baseline_directions_run_24)
            .into(this)
    }
}