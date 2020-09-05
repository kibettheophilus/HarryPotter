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
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
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


        binding.progressBar.visibility = GONE
        setRecyclerView(binding.root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterViewModel.characters.observe(viewLifecycleOwner){
            // hide
            when(it){
                is NetworkResult.Success -> {}
                is NetworkResult.Error ->{}
                is NetworkResult.Loading -> {}
            }

        }
        characterViewModel.characterError.observe(viewLifecycleOwner){
            // hide
        }
        characterViewModel.charactersResponse.observe(viewLifecycleOwner){
            // hide
        }

    }

    private fun setRecyclerView(view: View) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.progressBar.visibility = VISIBLE
        characterViewModel.getStudentCharacters().observe(viewLifecycleOwner,
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
                    binding.progressBar.visibility = GONE
                }
            })
    }

}
