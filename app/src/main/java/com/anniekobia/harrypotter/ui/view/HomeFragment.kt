package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

import com.anniekobia.harrypotter.R
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

        val adapter = com.anniekobia.harrypotter.ui.adapter.FragmentPagerAdapter(childFragmentManager)
        adapter.addFragment(StudentsFragment(),getString(R.string.students_fragment_tab_title))
        adapter.addFragment(StaffFragment(),getString(R.string.staff_fragment_tab_title))
        adapter.addFragment(OtherCharactersFragment(),getString(R.string.other_fragment_tab_title))

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        return view
    }
}
