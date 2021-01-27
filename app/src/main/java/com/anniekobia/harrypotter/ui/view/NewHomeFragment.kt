package com.anniekobia.harrypotter.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.FragmentHomeNewBinding
import com.anniekobia.harrypotter.ui.adapter.StudentDataAdapter
import com.anniekobia.harrypotter.viewmodel.CharacterViewModel
import com.anniekobia.harrypotter.utils.NetworkResult
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 */
class NewHomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeNewBinding
    private val characterViewModel: CharacterViewModel by viewModels()
    private var booleanFirstRun = false
    private lateinit var pref: SharedPreferences
    private lateinit var recyclerViewAdapter: StudentDataAdapter

    /**
     * Global variable isNetworkConnected to check for network connectivity
     */
    object Variables {
        var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue -> }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeNewBinding.inflate(inflater, container, false)

        startNetworkCallback()
        checkIfFirstRun()
        binding.refresh.setOnClickListener { loadAllCharacters() }

        return binding.root
    }

    /**
     * Checks for network connection state
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun startNetworkCallback() {
        val cm: ConnectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Variables.isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    Variables.isNetworkConnected = false
                }
            })
    }

    /**
     * Checks a SharedPreferences boolean value to get if its the first application run
     * If yes, invokes network call to fetch all characters else sets normal view with data
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun checkIfFirstRun() {
        pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        booleanFirstRun = pref.getBoolean("FIRST_RUN", false)
        if (!booleanFirstRun) {
            binding.progressBar.visibility = VISIBLE
            loadAllCharacters()
        } else {
            //Setup recyclerview after all data is fetched and saved in room
            setStudentRecyclerView(binding.root)
            characterViewModel.studentCharacters.observe(viewLifecycleOwner) {
                recyclerViewAdapter.submitList(it)
            }

        }

    }

    /**
     * Checks if there is a network connection before invoking network call to fetch all characters
     * Shows the error message view in case there is no connection or a network call error else sets normal view with data
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun loadAllCharacters() {
        binding.errorView.visibility = GONE
        binding.progressBar.visibility = VISIBLE
        if (Variables.isNetworkConnected) {
            characterViewModel.getAndSaveAllCharacters()
            characterViewModel.allCharacters.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Success -> {
                        binding.progressBar.visibility = GONE

                        val editor = pref.edit()
                        editor.putBoolean("FIRST_RUN", true)
                        editor.apply()

                        //Setup recyclerview after all data is fetched and saved in room
                        setStudentRecyclerView(binding.root)
                        characterViewModel.studentCharacters.observe(viewLifecycleOwner) {
                            recyclerViewAdapter.submitList(it)
                        }
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
     * Setup recyclerview
     */
    private fun setStudentRecyclerView(view: View) {
        recyclerViewAdapter =
            StudentDataAdapter { character: Character, imageView: ImageView ->
                val extras = FragmentNavigatorExtras(
                    imageView to character.image
                )

                val studentDetailsAction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
//                view.findNavController().navigate(studentDetailsAction,extras)
            }
        binding.studentRecyclerView.adapter = recyclerViewAdapter
    }

}

