package com.anniekobia.harrypotter.ui.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.api.model.Character
import com.squareup.picasso.Picasso


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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
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

        val character  = arguments?.getSerializable("Character") as Character
        bindDetails(character)

        return view
    }

    private fun bindDetails(character: Character) {
        //Loading image using Picasso
        Picasso.get().load(character.image).into(characterImage)
        characterName.text = character.name
        characterActorName.text = character.actor
        characterAncestry.text = character.ancestry
        characterPatronus.text = character.patronus
        characterWand.text = character.wand.wood+ " ,"+ character.wand.core+", "+character.wand.length+"cm"
        characterSpecies.text = character.species
        characterGender.text = character.gender
        characterEyeColor.text = character.eyeColour
        characterHairColor.text = character.hairColour
    }

}
