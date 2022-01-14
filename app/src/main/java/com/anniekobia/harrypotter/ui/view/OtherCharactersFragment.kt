package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.FragmentOtherCharactersBinding
import com.anniekobia.harrypotter.ui.adapter.CharacterDataAdapter
import com.anniekobia.harrypotter.utils.SpacesItemDecoration
import com.anniekobia.harrypotter.viewmodel.CharacterViewModel


/**
 * A simple [Fragment] subclass.
 */
class OtherCharactersFragment : Fragment() {

    private lateinit var recyclerViewAdapter: CharacterDataAdapter
    private val characterViewModel: CharacterViewModel by viewModels()
    private lateinit var binding: FragmentOtherCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentOtherCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(binding.root)

        characterViewModel.otherCharacters.observe(viewLifecycleOwner) {
            recyclerViewAdapter.submitList(it)
        }
    }

    /**
     * Set recyclerview adapter
     */
    private fun setRecyclerView(view: View) {
        recyclerViewAdapter =
            CharacterDataAdapter { character: Character, imageView: ImageView ->
                val extras = FragmentNavigatorExtras(
                    imageView to character.image
                )

                val otherDetailsAction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
                view.findNavController().navigate(otherDetailsAction,extras)
            }
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.addItemDecoration(SpacesItemDecoration(20))
        binding.recyclerView.adapter = recyclerViewAdapter
    }
}
