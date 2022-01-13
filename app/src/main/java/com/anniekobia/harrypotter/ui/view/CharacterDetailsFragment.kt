package com.anniekobia.harrypotter.ui.view

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.remote.model.CharacterTwoListItem
import com.anniekobia.harrypotter.databinding.FragmentCharacterDetailsBinding
import com.anniekobia.harrypotter.utils.loadUrl
import java.util.*


class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val detailsFragmentArgs : CharacterDetailsFragmentArgs by navArgs()
    private lateinit var characterImageUri: String

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        /**
         * Shared element transition for character image from recyclerview item to details page
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }

        val character = detailsFragmentArgs.character
        characterImageUri = character?.image.toString()

        character?.let { character ->
            bindDetails(character)
        }
        return binding.root
    }


    @ExperimentalStdlibApi
    private fun bindDetails(character: CharacterTwoListItem) {
        binding.characterImage.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionName = characterImageUri
            }
            binding.characterImage.loadUrl(character.image)
        }
        binding.characterName.text = character.name
        binding.characterActorName.text = character.actor

        if (character.ancestry.isNotEmpty()) {
            binding.ancestryCL.visibility = VISIBLE
            binding.ancestry.text = (character.ancestry).capitalize(Locale.ROOT)
        }
        if (character.patronus.isNotEmpty()) {
            binding.patronusCL.visibility = VISIBLE
            binding.patronus.text = (character.patronus).capitalize(Locale.ROOT)
        }
        if (character.eyeColour.isNotEmpty()) {
            binding.eyeColorCL.visibility = VISIBLE
            binding.eyeColor.text = (character.eyeColour).capitalize(Locale.ROOT)
        }
        if (character.house.isNotEmpty()) {
            binding.houseCL.visibility = VISIBLE
            binding.house.text =
                (character.house + "  " + getString(R.string.pointing_emoji)).capitalize(Locale.ROOT)

            binding.housePlayButton.setOnClickListener {
                val studentDetailsAction = CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToHouseDetailsDialogFragment(
                    character
                )
                findNavController().navigate(studentDetailsAction)
            }
        }
        if (character.species.isNotEmpty()) {
            binding.speciesCL.visibility = VISIBLE
            binding.species.text = (character.species).capitalize(Locale.ROOT)
        }
        if (character.gender.isNotEmpty()) {
            binding.genderCL.visibility = VISIBLE
            binding.gender.text = (character.gender).capitalize(Locale.ROOT)
        }
        if (character.hairColour.isNotEmpty()) {
            binding.hairColorCL.visibility = VISIBLE
            binding.hairColor.text = (character.hairColour).capitalize(Locale.ROOT)
        }
        if ((character.wand.wood + character.wand.core + character.wand.length).isNotEmpty()) {
            binding.wandCL.visibility = VISIBLE
            when {
                character.wand.wood.isNotEmpty() && character.wand.core.isEmpty() && character.wand.length.isEmpty() -> {
                    binding.wand.text = character.wand.wood.capitalize(Locale.ROOT)
                }
                character.wand.wood.isNotEmpty() && character.wand.core.isNotEmpty() && character.wand.length.isEmpty() -> {
                    binding.wand.text =
                        (character.wand.wood + ", " + character.wand.core + getString(R.string.wand_core)).capitalize(
                            Locale.ROOT
                        )
                }
                character.wand.wood.isNotEmpty() && character.wand.core.isEmpty() && character.wand.length.isNotEmpty() -> {
                    binding.wand.text =
                        (character.wand.wood + ", " + character.wand.length + getString(R.string.wand_inches)).capitalize(
                            Locale.ROOT
                        )
                }
                character.wand.wood.isEmpty() && character.wand.core.isNotEmpty() && character.wand.length.isNotEmpty() -> {
                    binding.wand.text =
                        (character.wand.core + getString(R.string.wand_core) + ", " + character.wand.length + getString(
                            R.string.wand_inches
                        )).capitalize(Locale.ROOT)
                }
                else -> {
                    binding.wand.text =
                        (character.wand.wood + ", " + character.wand.core + getString(R.string.wand_core) + ", " + character.wand.length + getString(
                            R.string.wand_inches
                        )).capitalize(Locale.ROOT)
                }
            }
        }
    }

}
