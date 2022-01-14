package com.example.searchgit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.searchgit.R
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
        val viewPager = startFragmentBinding.viewPagerBottomNav
        val tabLayout = startFragmentBinding.tabLayoutBottomNav
        val bottomNavLayout: View = this.layoutInflater.inflate(R.layout.bottom_nav,null,false)
        val relativeLayoutList = listOf<RelativeLayout>(
            bottomNavLayout.findViewById(R.id.btn_bottom_navi_search),
            bottomNavLayout.findViewById(R.id.btn_bottom_navi_database),
            bottomNavLayout.findViewById(R.id.btn_bottom_navi_third)
        )
        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout,viewPager){
                tab: TabLayout.Tab,
                i: Int -> tab.customView = relativeLayoutList[i]
        }.attach()
    }
////text = "OBJECT ${(i + 1)}"
//    private fun customizingTabLayout(){
//        val tabLayout = startFragmentBinding.tabLayoutBottomNav
//        val bottomNavLayout: View = this.layoutInflater.inflate(R.layout.bottom_nav,null,false)
//        tabLayout.getTabAt(0)?.customView = bottomNavLayout.findViewById(R.id.btn_bottom_navi_search) as RelativeLayout
//        tabLayout.getTabAt(1)?.customView = bottomNavLayout.findViewById(R.id.btn_bottom_navi_database) as RelativeLayout
//        tabLayout.getTabAt(2)?.customView = bottomNavLayout.findViewById(R.id.btn_bottom_navi_third) as RelativeLayout
//
//
//    }

}