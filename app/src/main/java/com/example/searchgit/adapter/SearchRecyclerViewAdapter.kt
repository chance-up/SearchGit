package com.example.searchgit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgit.data.GitUser
import com.example.searchgit.databinding.ItemGituserBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SearchRecyclerViewAdapter @Inject constructor() :
    ListAdapter<GitUser, SearchRecyclerViewAdapter.MyViewHolder>(diffUtil) {

    var onClickLikeBtn: ((Int) -> Unit)? = null

    inner class MyViewHolder(private val binding: ItemGituserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gitUser: GitUser) {
            binding.gitUser = gitUser
            binding.buttonLike.setOnClickListener {
                onClickLikeBtn?.invoke(layoutPosition)
            }
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
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }

}
