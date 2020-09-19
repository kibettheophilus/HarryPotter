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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(binding.root)

        characterViewModel.newCharacters.observe(viewLifecycleOwner) {
            recyclerViewAdapter.submitList(it)
        }
    }

    /**
     * Fetches student characters in the local sqlite db
     * Shows the error message view in case there are no characters
     */
    private fun loadStudentCharacters() {
        binding.errorView.visibility = GONE
        characterViewModel.getStudentCharacters()

    }

    /**
     * Set recyclerview adapter with list fetched from local sqlite db
     */
    private fun setRecyclerView(view: View) {
        recyclerViewAdapter =
            CharacterDataAdapter { character: Character, imageView: ImageView ->
                val extras = FragmentNavigatorExtras(
                    imageView to character.image
                )

                val studentDetailsAction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
                view.findNavController().navigate(studentDetailsAction,extras)
            }
        binding.recyclerView.adapter = recyclerViewAdapter
    }

}
