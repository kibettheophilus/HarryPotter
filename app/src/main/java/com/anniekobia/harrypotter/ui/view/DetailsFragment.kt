package com.anniekobia.harrypotter.ui.view

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.api.model.Character
import com.squareup.picasso.Picasso
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    lateinit var characterImage: ImageView
    lateinit var characterName: TextView
    lateinit var characterActorName: TextView
    lateinit var characterAncestry: TextView
    lateinit var characterPatronus: TextView
    lateinit var characterWand: TextView
    lateinit var characterSpecies: TextView
    lateinit var characterGender: TextView
    lateinit var characterEyeColor: TextView
    lateinit var characterHairColor: TextView
    lateinit var characterHouse: TextView
    lateinit var characterHouseLogo: ImageView
    lateinit var houseLogoAnimation: Animation
    lateinit var characterImageUri: String

    lateinit var characterAncestryTxt: TextView
    lateinit var characterPatronusTxt: TextView
    lateinit var characterWandTxt: TextView
    lateinit var characterSpeciesTxt: TextView
    lateinit var characterGenderTxt: TextView
    lateinit var characterEyeColorTxt: TextView
    lateinit var characterHairColorTxt: TextView
    lateinit var characterHouseTxt: TextView

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        characterImage = view.findViewById(R.id.character_image)
        characterName = view.findViewById(R.id.character_name)
        characterActorName = view.findViewById(R.id.character_actor_name)
        characterAncestry = view.findViewById(R.id.ancestry)
        characterPatronus = view.findViewById(R.id.patronus)
        characterWand = view.findViewById(R.id.wand)
        characterSpecies = view.findViewById(R.id.species)
        characterGender = view.findViewById(R.id.gender)
        characterEyeColor = view.findViewById(R.id.eye_color)
        characterHairColor = view.findViewById(R.id.hair_color)
        characterHouse = view.findViewById(R.id.house)
        characterHouseLogo = view.findViewById(R.id.house_logo)

        characterAncestryTxt = view.findViewById(R.id.ancestry_txt)
        characterPatronusTxt = view.findViewById(R.id.patronus_txt)
        characterWandTxt = view.findViewById(R.id.wand_txt)
        characterSpeciesTxt = view.findViewById(R.id.species_txt)
        characterGenderTxt = view.findViewById(R.id.gender_txt)
        characterEyeColorTxt = view.findViewById(R.id.eye_color_txt)
        characterHairColorTxt = view.findViewById(R.id.hair_color_txt)
        characterHouseTxt = view.findViewById(R.id.house_txt)

        //Animation
        houseLogoAnimation = AnimationUtils.loadAnimation(context, R.anim.house_logo_animation)

        //Shared element transition for character image
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }

        val character = arguments?.getSerializable("Character") as Character
        characterImageUri = arguments?.getString("URI") as String

        bindDetails(character)
        setHouseLogo(character)
        return view
    }


    @ExperimentalStdlibApi
    private fun bindDetails(character: Character) {

        characterImage.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionName = characterImageUri
            }
            //Loading image using Picasso
            Picasso.get().load(character.image).into(characterImage)
        }
        characterName.text = character.name
        characterActorName.text = character.actor

        if (character.ancestry != "") {
            characterAncestry.visibility = VISIBLE
            characterAncestryTxt.visibility = VISIBLE
            characterAncestry.text = (character.ancestry).capitalize(Locale.ROOT)
        }
        if (character.patronus != "") {
            characterPatronus.visibility = VISIBLE
            characterPatronusTxt.visibility = VISIBLE
            characterPatronus.text = (character.patronus).capitalize(Locale.ROOT)
        }
        if (character.eyeColour != "") {
            characterEyeColor.visibility = VISIBLE
            characterEyeColorTxt.visibility = VISIBLE
            characterEyeColor.text = (character.eyeColour).capitalize(Locale.ROOT)
        }
        if (character.house != "") {
            characterHouse.visibility = VISIBLE
            characterHouseTxt.visibility = VISIBLE
            characterHouse.text =
                (character.house + getString(R.string.pointing_emoji)).capitalize(Locale.ROOT)
        }
        if (character.species != "") {
            characterSpecies.visibility = VISIBLE
            characterSpeciesTxt.visibility = VISIBLE
            characterSpecies.text = (character.species).capitalize(Locale.ROOT)
        }
        if (character.gender != "") {
            characterGender.visibility = VISIBLE
            characterGenderTxt.visibility = VISIBLE
            characterGender.text = (character.gender).capitalize(Locale.ROOT)
        }
        if (character.hairColour != "") {
            characterHairColor.visibility = VISIBLE
            characterHairColorTxt.visibility = VISIBLE
            characterHairColor.text = (character.hairColour).capitalize(Locale.ROOT)
        }
        if (character.wand.wood + character.wand.core + character.wand.length != "") {
            characterWand.visibility = VISIBLE
            characterWandTxt.visibility = VISIBLE
            when {
                character.wand.wood != "" && character.wand.core == "" && character.wand.length == "" -> {
                    characterWand.text = character.wand.wood.capitalize(Locale.ROOT)
                }
                character.wand.wood != "" && character.wand.core != "" && character.wand.length == "" -> {
                    characterWand.text =
                        (character.wand.wood + ", " + character.wand.core + " core").capitalize(Locale.ROOT)
                }
                character.wand.wood != "" && character.wand.core == "" && character.wand.length != "" -> {
                    characterWand.text =
                        (character.wand.wood + ", " + character.wand.length + "inches").capitalize(Locale.ROOT)
                }
                character.wand.wood == "" && character.wand.core != "" && character.wand.length != "" -> {
                    characterWand.text =
                        (character.wand.core + " core, " + character.wand.length + "inches").capitalize(Locale.ROOT)
                }
                else -> {
                    characterWand.text =
                        (character.wand.wood + ", " + character.wand.core + " core, " + character.wand.length + "inches").capitalize(Locale.ROOT)
                }
            }
        }


    }

    private fun setHouseLogo(character: Character) {
        when (character.house) {
            "Gryffindor" -> {
                characterHouseLogo.setImageResource(R.drawable.logo_gryffindor)
                characterHouseLogo.startAnimation(houseLogoAnimation)
            }
            "Slytherin" -> {
                characterHouseLogo.setImageResource(R.drawable.logo_slytherin)
                characterHouseLogo.startAnimation(houseLogoAnimation)
            }
            "Ravenclaw" -> {
                characterHouseLogo.setImageResource(R.drawable.logo_ravenclaw)
                characterHouseLogo.startAnimation(houseLogoAnimation)
            }
            "Hufflepuff" -> {
                characterHouseLogo.setImageResource(R.drawable.logo_hufflepuff)
                characterHouseLogo.startAnimation(houseLogoAnimation)
            }
        }
    }

}
