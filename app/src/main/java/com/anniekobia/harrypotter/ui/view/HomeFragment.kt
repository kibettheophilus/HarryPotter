package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val adapter = com.anniekobia.harrypotter.ui.adapter.FragmentPagerAdapter(childFragmentManager)
        adapter.addFragment(StudentsFragment(),getString(R.string.students_fragment_tab_title))
        adapter.addFragment(StaffFragment(),getString(R.string.staff_fragment_tab_title))
        adapter.addFragment(OtherCharactersFragment(),getString(R.string.other_fragment_tab_title))

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        return binding.root
    }
}