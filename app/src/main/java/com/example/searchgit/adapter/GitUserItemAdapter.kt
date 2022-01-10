package com.example.searchgit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.R
import com.example.searchgit.model.GitUser
import kotlinx.android.synthetic.main.item_gituser.view.*
//
//class GitUserItemAdapter(private var gitUsers: ArrayList<GitUser>): RecyclerView.Adapter<GitUserItemAdapter.MyViewHolder>() {
//
//    class MyViewHolder(view: View, listener: GitUserItemAdapter.OnGitUserClickListener?):RecyclerView.ViewHolder(view) {
//        private val avatarView:ImageView = view.avatarView
//        private val idTextView:TextView = view.idTextView
//        private val urlTextView:TextView = view.urlTextView
//        init {
//            view.setOnClickListener{
//                listener?.onItemClick(adapterPosition)
//            }
//        }
//
//        fun bind(model:GitUser){
//            model.run {
//                avatarView.setImageWithGlide(image)
//                idTextView.text = id
//                urlTextView.text = url
//            }
//        }
//
//    }
//
//
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
////        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gituser,parent,false)
////        return MyViewHolder(view,listener)
////    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int  = gitUsers.size
//
//
//}

private fun ImageView.setImageWithGlide(image: String) {

    Glide.with(this)
        .load(image)
        .into(this)

}
