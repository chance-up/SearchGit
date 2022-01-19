package com.example.searchgit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgit.databinding.ItemGituserBinding
import com.example.searchgit.model.GitUser

class SearchRecyclerViewAdapter():RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder>(){

    var gitUsers = mutableListOf<GitUser>()

    class MyViewHolder(private val binding: ItemGituserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:GitUser){
            binding.apply {
                binding.gitUser = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerViewAdapter.MyViewHolder {
        return MyViewHolder(
            ItemGituserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: SearchRecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.apply { bind(gitUsers[position]) }
    }

    override fun getItemCount(): Int = gitUsers.size


}
