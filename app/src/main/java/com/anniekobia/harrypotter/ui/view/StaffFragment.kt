package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.anniekobia.harrypotter.ui.adapter.CharacterDataAdapter

import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.FragmentStaffBinding
import com.anniekobia.harrypotter.ui.viewmodel.CharacterViewModel

/**
 * A simple [Fragment] subclass.
 */
class StaffFragment : Fragment() {

    private lateinit var recyclerViewAdapter: CharacterDataAdapter
    private val characterViewModel: CharacterViewModel by viewModels()

    private lateinit var binding: FragmentStaffBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentStaffBinding.inflate(inflater, container, false)

        binding.progressBar.visibility = View.GONE
        setRecyclerView(binding.root)

        return binding.root
    }

    private fun setRecyclerView(view: View) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.progressBar.visibility = View.VISIBLE
        characterViewModel.getStaffCharacters().observe(viewLifecycleOwner,
                Observer<ArrayList<Character>> { characters ->
                    if (characters != null) {
                        recyclerViewAdapter =
                            CharacterDataAdapter(characters) { character: Character, imageView: ImageView ->
                                val bundle = bundleOf("Character" to character,"URI" to character.image)
                                val extras = FragmentNavigatorExtras(
                                    imageView to character.image
                                )
                                view.findNavController().navigate(R.id.global_detailsFragment, bundle,null,extras)
                            }
                        binding.recyclerView.adapter = recyclerViewAdapter
                        binding.progressBar.visibility = View.GONE
                    }
                })
    }


}
