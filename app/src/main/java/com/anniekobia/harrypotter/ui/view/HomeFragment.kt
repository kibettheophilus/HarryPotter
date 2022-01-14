package com.anniekobia.harrypotter.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.databinding.FragmentHomeBinding
import com.anniekobia.harrypotter.ui.adapter.ViewPagerFragmentStateAdapter
import com.anniekobia.harrypotter.viewmodel.CharacterViewModel
import com.anniekobia.harrypotter.utils.NetworkResult
import com.google.android.material.tabs.TabLayoutMediator


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val characterViewModel: CharacterViewModel by viewModels()
    private var booleanFirstRun = false
    private lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        (activity as MainActivity).startNetworkCallback()
        checkIfFirstRun()
        binding.refresh.setOnClickListener { loadAllCharacters() }
        binding.harryPotterGlasses.setOnClickListener { loadAllCharacters() }

        return binding.root
    }

    /**
     * Checks a SharedPreferences boolean value to get if its the first application run
     * If yes, invokes network call to fetch all characters else sets normal view with data
     */
    private fun checkIfFirstRun() {
        pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        booleanFirstRun = pref.getBoolean("FIRST_RUN", false)
        if (!booleanFirstRun) {
            binding.progressBar.visibility = VISIBLE
            loadAllCharacters()
        } else {
            setViewPager()
        }

    }

    /**
     * Checks if there is a network connection before invoking network call to fetch all characters
     * Shows the error message view in case there is no connection or a network call error else sets normal view with data
     */
    private fun loadAllCharacters() {
        binding.errorView.visibility = GONE
        binding.progressBar.visibility = VISIBLE
        if (MainActivity.Variables.isNetworkConnected) {
            characterViewModel.getAndSaveAllCharacters()
            characterViewModel.allCharacters.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = GONE

                        val editor = pref.edit()
                        editor.putBoolean("FIRST_RUN", true)
                        editor.apply()

                        setViewPager()
                    }
                    is NetworkResult.Error -> {
                        binding.progressBar.visibility = GONE
                        binding.message.text = it.exception.message
                        binding.errorView.visibility = VISIBLE
                    }
                }
            }
        } else {
            binding.progressBar.visibility = GONE
            binding.message.text = getString(R.string.no_internet_message)
            binding.errorView.visibility = VISIBLE
        }
    }

    /**
     * Sets ViewPager and TabLayout
     */
    private fun setViewPager() {
        val adapter = ViewPagerFragmentStateAdapter(this)
        binding.viewPager.adapter = adapter

        //Tab titles
        val titles = arrayOf(
            getString(R.string.students_fragment_tab_title),
            getString(R.string.staff_fragment_tab_title),
            getString(R.string.other_fragment_tab_title)
        )
        adapter.addFragment(StudentsFragment())
        adapter.addFragment(StaffFragment())
        adapter.addFragment(OtherCharactersFragment())
        //Attach tab mediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = (titles[position])
        }.attach()
    }

}

