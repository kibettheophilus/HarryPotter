package com.anniekobia.marvel.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

import com.anniekobia.marvel.R
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        tabs = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.viewPager)

        val adapter = MyFragmentPagerAdapter(childFragmentManager)
        adapter.addFragment(AtoZFragment(),"A-Z")
//        adapter.addFragment(LastViewedFragment(),"Last Viewed")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        return view
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
