package com.example.searchgit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.searchgit.adapter.ViewPagerAdapter
import com.example.searchgit.databinding.FragmentStartBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StartFragment : Fragment() {

    private lateinit var startFragmentBinding : FragmentStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        startFragmentBinding = FragmentStartBinding.inflate(inflater,container,false)
        return startFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFragmentBinding.viewPagerBottomNav.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(startFragmentBinding.tabLayoutBottomNav,startFragmentBinding.viewPagerBottomNav){
                tab: TabLayout.Tab,
                i: Int -> tab.text = "OBJECT ${(i + 1)}"
        }.attach()
    }
}