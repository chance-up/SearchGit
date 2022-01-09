package com.example.searchgit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.searchgit.ui.FirstFragment
import com.example.searchgit.ui.SecondFragment
import com.example.searchgit.ui.ThirdFragment

class BottomNavAdapter(fm :FragmentManager, private val fragmentCount:Int) :FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getCount(): Int = fragmentCount

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> FirstFragment()
        }
    }
}