package com.anniekobia.harrypotter.ui.view

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.FragmentDetailsBinding
import com.anniekobia.harrypotter.utils.loadImage
import java.util.*


class DetailsFragment : Fragment() {

    private lateinit var houseLogoAnimation: Animation
    private lateinit var characterImageUri: String

    private lateinit var binding: FragmentDetailsBinding

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        /**
         * Load character house logo animation
         */
        houseLogoAnimation = AnimationUtils.loadAnimation(context, R.anim.house_logo_animation)


        /**
         * Shared element transition for character image from recyclerview item to details page
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }

        val character = arguments?.getSerializable("Character") as Character
        characterImageUri = arguments?.getString("URI") as String

        bindDetails(character)
        setHouseLogo(character)
        return binding.root
    }


    @ExperimentalStdlibApi
    private fun bindDetails(character: Character) {
        binding.characterImage.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionName = characterImageUri
            }
//            Picasso.get().load(character.image).into(binding.characterImage)
            loadImage(character.image, binding.characterImage)
        }
        binding.characterName.text = character.name
        binding.characterActorName.text = character.actor

        if (character.ancestry.isNotEmpty()) {
            binding.ancestry.visibility = VISIBLE
            binding.ancestryTxt.visibility = VISIBLE
            binding.ancestry.text = (character.ancestry).capitalize(Locale.ROOT)
        }
        if (character.patronus.isNotEmpty()) {
            binding.patronus.visibility = VISIBLE
            binding.patronusTxt.visibility = VISIBLE
            binding.patronus.text = (character.patronus).capitalize(Locale.ROOT)
        }
        if (character.eyeColour.isNotEmpty()) {
            binding.eyeColor.visibility = VISIBLE
            binding.eyeColorTxt.visibility = VISIBLE
            binding.eyeColor.text = (character.eyeColour).capitalize(Locale.ROOT)
        }
        if (character.house.isNotEmpty()) {
            binding.house.visibility = VISIBLE
            binding.houseTxt.visibility = VISIBLE
            binding.house.text =
                (character.house + getString(R.string.pointing_emoji)).capitalize(Locale.ROOT)
        }
        if (character.species.isNotEmpty()) {
            binding.species.visibility = VISIBLE
            binding.speciesTxt.visibility = VISIBLE
            binding.species.text = (character.species).capitalize(Locale.ROOT)
        }
        if (character.gender.isNotEmpty()) {
            binding.gender.visibility = VISIBLE
            binding.genderTxt.visibility = VISIBLE
            binding.gender.text = (character.gender).capitalize(Locale.ROOT)
        }
        if (character.hairColour.isNotEmpty()) {
            binding.hairColor.visibility = VISIBLE
            binding.hairColorTxt.visibility = VISIBLE
            binding.hairColor.text = (character.hairColour).capitalize(Locale.ROOT)
        }
        if ((character.wand.wood + character.wand.core + character.wand.length).isNotEmpty()) {
            binding.wand.visibility = VISIBLE
            binding.wandTxt.visibility = VISIBLE
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

    /**
     * Set respective house logo and start logo animation
     */
    private fun setHouseLogo(character: Character) {
        when (character.house) {
            getString(R.string.house_gryffindor) -> {
                binding.houseLogo.setImageResource(R.drawable.logo_gryffindor)
                binding.houseLogo.startAnimation(houseLogoAnimation)
            }
            getString(R.string.house_slytherin) -> {
                binding.houseLogo.setImageResource(R.drawable.logo_slytherin)
                binding.houseLogo.startAnimation(houseLogoAnimation)
            }
            getString(R.string.house_ravenclaw) -> {
                binding.houseLogo.setImageResource(R.drawable.logo_ravenclaw)
                binding.houseLogo.startAnimation(houseLogoAnimation)
            }
            getString(R.string.house_hufflepuff) -> {
                binding.houseLogo.setImageResource(R.drawable.logo_hufflepuff)
                binding.houseLogo.startAnimation(houseLogoAnimation)
            }
        }
    }

}
