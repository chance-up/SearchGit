package com.example.searchgit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgit.databinding.ItemGituserBinding
import com.example.searchgit.data.GitUser

class SearchRecyclerViewAdapter :
    ListAdapter<GitUser, SearchRecyclerViewAdapter.MyViewHolder>(diffUtil) {

    class MyViewHolder(private val binding: ItemGituserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gitUser: GitUser) {
            binding.gitUser = gitUser
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ItemGituserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position))
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GitUser>() {
            override fun areItemsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
                Log.e("ccs","areItemsTheSame :: ${oldItem==newItem}")
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
                Log.e("ccs","areContentsTheSame :: $oldItem $newItem")
                return oldItem.url == newItem.url
            }
        }
    }

}
