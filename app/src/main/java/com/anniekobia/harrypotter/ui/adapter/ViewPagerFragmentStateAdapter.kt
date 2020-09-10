package com.anniekobia.harrypotter.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anniekobia.harrypotter.ui.view.HomeFragment


class ViewPagerFragmentStateAdapter(fragmentActivity: HomeFragment) :
    FragmentStateAdapter(fragmentActivity) {
    private val mFragmentList: ArrayList<Fragment> = ArrayList()

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }
}
