package com.anniekobia.marvel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.anniekobia.marvel.R
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabs = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewPager)

        val adapter = MyFragmentPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentAtoZ(),"A-Z")
        adapter.addFragment(FragmentLastViewed(),"Last Viewed")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        tabs.setSelectedTabIndicator(R.drawable.selected_tab)
    }


    private inner class MyFragmentPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentList: ArrayList<Fragment> = ArrayList()
        private val mFragmentTitleList: ArrayList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {

            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment,title : String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }

}
