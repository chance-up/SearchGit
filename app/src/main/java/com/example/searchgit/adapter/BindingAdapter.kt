package com.example.searchgit.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchgit.model.GitUser

object BindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    fun RecyclerView.setItem(items:MutableList<GitUser>?){
        if(adapter ==null && items != null){
            val adapter = SearchRecyclerViewAdapter().apply {
                gitUsers = items
            }
            setAdapter(adapter)
            adapter.notifyDataSetChanged()
        }
//        val gitUserAdapter = recyclerView.adapter as SearchRecyclerViewAdapter
//        gitUserAdapter.gitUsers = items!!
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(imageUrl : String){
        Glide.with(context)
            .load(imageUrl)
            .error(com.example.searchgit.R.drawable.ic_baseline_directions_run_24)
            .into(this)
    }
}