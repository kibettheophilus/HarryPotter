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
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.anniekobia.harrypotter.ui.adapter.CharacterDataAdapter

import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.FragmentStaffBinding
import com.anniekobia.harrypotter.ui.viewmodel.CharacterViewModel
import com.anniekobia.harrypotter.utils.NetworkResult

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

        loadStaffCharacters()

        return binding.root
    }

    /**
     * Fetches staff characters in the local sqlite db
     * Shows the error message view incase there are no characters
     */
    private fun loadStaffCharacters() {
        binding.errorView.visibility = View.GONE
        characterViewModel.getStaffCharacters()
        characterViewModel.characters.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    setRecyclerView(binding.root, it.data as ArrayList<Character>)
                }
                is NetworkResult.Error -> {
                    binding.message.text = it.exception.message
                    binding.errorView.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setRecyclerView(view: View, characters: ArrayList<Character>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerViewAdapter =
            CharacterDataAdapter(characters) { character: Character, imageView: ImageView ->
                val bundle = bundleOf("Character" to character, "URI" to character.image)
                val extras = FragmentNavigatorExtras(
                    imageView to character.image
                )
                view.findNavController().navigate(R.id.global_detailsFragment, bundle, null, extras)
            }
        binding.recyclerView.adapter = recyclerViewAdapter
    }


}
