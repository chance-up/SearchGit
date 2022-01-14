package com.example.searchgit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgit.databinding.ItemGituserBinding
import com.example.searchgit.model.GitUser

class GitUserItemAdapter():RecyclerView.Adapter<GitUserItemAdapter.MyViewHolder>(){
    var gitUsers = mutableListOf<GitUser>()
    class MyViewHolder(private val binding: ItemGituserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:GitUser){
            binding.apply {
                binding.gitUser = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserItemAdapter.MyViewHolder {
        return MyViewHolder(
            ItemGituserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: GitUserItemAdapter.MyViewHolder, position: Int) {
        holder.apply { bind(gitUsers[position]) }
    }

    override fun getItemCount(): Int = gitUsers.size


}
