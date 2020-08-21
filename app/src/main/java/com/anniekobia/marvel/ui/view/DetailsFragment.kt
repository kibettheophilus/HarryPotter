package com.anniekobia.marvel.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anniekobia.marvel.R
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    lateinit var superheroImage: ImageView
    lateinit var superheroName: TextView
    lateinit var superheroCharacterName: TextView
    lateinit var superheroBio: TextView
    lateinit var superheroAppearanceGender: TextView
    lateinit var superheroAppearanceRace: TextView
    lateinit var superheroAliases: TextView
    lateinit var superheroComics: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        superheroImage = view.findViewById(R.id.superhero_image)
        superheroName = view.findViewById(R.id.superhero_name)
        superheroCharacterName = view.findViewById(R.id.superhero_character_name)
        superheroBio = view.findViewById(R.id.superhero_bio)
        superheroAppearanceGender = view.findViewById(R.id.superhero_appearance_gender_txt)
        superheroAppearanceRace = view.findViewById(R.id.superhero_appearance_race_txt)
        superheroAliases = view.findViewById(R.id.superhero_aliases_list)
        superheroComics = view.findViewById(R.id.superhero_comics_list)

        val superhero  = arguments?.getSerializable("Superhero") as MarvelSuperhero
        bindDetails(superhero)

        return view
    }

    private fun bindDetails(superhero: MarvelSuperhero) {
        //Loading image using Picasso
        Picasso.get().load(superhero.superheroImage).into(superheroImage)
        superheroName.text = superhero.superheroName
        superheroCharacterName.text = superhero.superheroCharacterName
        superheroBio.text = superhero.superheroDescription
        superheroAppearanceGender.text = superhero.superheroAppearanceGender
        superheroAppearanceRace.text = superhero.superheroAppearanceRace
        superheroAliases.text = superhero.superheroAliases.distinct().joinToString(separator = "\n")
        superheroComics.text = superhero.superheroComics.distinct().joinToString(separator = "\n")
    }

}
