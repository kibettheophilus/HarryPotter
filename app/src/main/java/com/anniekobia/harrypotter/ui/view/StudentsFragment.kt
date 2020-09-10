package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.FragmentStudentBinding
import com.anniekobia.harrypotter.ui.adapter.CharacterDataAdapter
import com.anniekobia.harrypotter.ui.viewmodel.CharacterViewModel
import com.anniekobia.harrypotter.utils.NetworkResult


/**
 * A simple [Fragment] subclass.
 */
class StudentsFragment : Fragment() {

    private lateinit var recyclerViewAdapter: CharacterDataAdapter
    private val characterViewModel: CharacterViewModel by viewModels()

    private lateinit var binding: FragmentStudentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentStudentBinding.inflate(inflater, container, false)

        loadStudentCharacters()

        return binding.root
    }

    /**
     * Fetches student characters in the local sqlite db
     * Shows the error message view in case there are no characters
     */
    private fun loadStudentCharacters() {
        binding.errorView.visibility = GONE
        characterViewModel.getStudentCharacters()
        characterViewModel.characters.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    setRecyclerView(binding.root, it.data as ArrayList<Character>)
                }
                is NetworkResult.Error -> {
                    binding.message.text = it.exception.message
                    binding.errorView.visibility = VISIBLE
                }
            }
        }

    }

    /**
     * Set recyclerview adapter with list fetched from local sqlite db
     */
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
