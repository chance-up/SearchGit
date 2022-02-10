package com.example.searchgit.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.searchgit.ui.DatabaseFragment
import com.example.searchgit.ui.SearchFragment
import com.example.searchgit.ui.ThirdFragment

class ViewPagerAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {
    private val fragmentList=listOf(
        SearchFragment(),
        DatabaseFragment(),
        ThirdFragment()
    )
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList[position]
}