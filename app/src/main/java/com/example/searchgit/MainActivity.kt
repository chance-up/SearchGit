package com.example.searchgit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.example.searchgit.adapter.BottomNavAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureBottomNav()

    }

    private fun configureBottomNav(){
        //findViewById<ViewPager>
        viewPagerBottomNav.adapter = BottomNavAdapter(supportFragmentManager,3)
        tabLayoutBottomNav.setupWithViewPager(viewPagerBottomNav)

        val bottomNavLayout: View = this.layoutInflater.inflate(R.layout.bottom_nav,null,false)

        tabLayoutBottomNav.getTabAt(0)?.customView = bottomNavLayout.findViewById(R.id.btn_bottom_navi_first) as RelativeLayout
        tabLayoutBottomNav.getTabAt(1)?.customView = bottomNavLayout.findViewById(R.id.btn_bottom_navi_second) as RelativeLayout
        tabLayoutBottomNav.getTabAt(2)?.customView = bottomNavLayout.findViewById(R.id.btn_bottom_navi_third) as RelativeLayout

    }
}