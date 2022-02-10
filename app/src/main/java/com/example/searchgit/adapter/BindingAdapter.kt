package com.example.searchgit.adapter

import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchgit.data.GitUser

object BindingAdapter {

    @BindingAdapter("items")
    @JvmStatic
    fun RecyclerView.setItems(gitUserList: ArrayList<GitUser>?) {
        (this.adapter as? SearchRecyclerViewAdapter)?.submitList(gitUserList?.toMutableList())
    }

//    @BindingAdapter("items")
//    @JvmStatic
//    fun <T>RecyclerView.setItems(gitUserList : ArrayList<GitUser>?){
//        (adapter as? ListAdapter<T,*>)?.submitList(gitUserList as MutableList<T>?)
//    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .error(com.example.searchgit.R.drawable.ic_baseline_directions_run_24)
            .into(this)
    }

    @BindingAdapter("activateButton")
    @JvmStatic
    fun Button.activateButton(isLike: Boolean) {
        if (!isLike) {
            this.setBackgroundColor(Color.rgb(99, 0, 255))
            this.text = "Like"

        } else {
            this.setBackgroundColor(Color.DKGRAY)
            this.text = "DisLike"
        }
    }

}