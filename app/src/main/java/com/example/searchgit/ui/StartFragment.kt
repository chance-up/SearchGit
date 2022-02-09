package com.example.searchgit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.searchgit.R
import com.example.searchgit.adapter.ViewPagerAdapter
import com.example.searchgit.databinding.BottomNavBinding
import com.example.searchgit.databinding.FragmentStartBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StartFragment : Fragment() {

    private lateinit var startFragmentBinding : FragmentStartBinding
    private lateinit var bottomNavBinding: BottomNavBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bottomNavBinding = BottomNavBinding.inflate(inflater,container,false)
        startFragmentBinding = FragmentStartBinding.inflate(inflater,container,false)
        return startFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = startFragmentBinding.viewPagerBottomNav
        val tabLayout = startFragmentBinding.tabLayoutBottomNav
        val relativeLayoutList = listOf(
            bottomNavBinding.btnBottomNaviSearch,
            bottomNavBinding.btnBottomNaviDatabase ,
            bottomNavBinding.btnBottomNaviThird
        )
        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout,viewPager){
                tab: TabLayout.Tab,
                i: Int -> tab.customView = relativeLayoutList[i]
        }.attach()
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
}