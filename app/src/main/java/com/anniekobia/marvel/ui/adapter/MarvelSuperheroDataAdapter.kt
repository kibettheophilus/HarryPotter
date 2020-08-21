package com.anniekobia.marvel.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.R
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.squareup.picasso.Picasso


class MarvelSuperheroDataAdapter(
    private var characterList: ArrayList<MarvelSuperhero>,
    private val listener: (MarvelSuperhero) -> Unit
) : RecyclerView.Adapter<MarvelSuperheroDataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val marvelSuperhero = characterList[position]

            holder.superheroName.text = marvelSuperhero.superheroName
            holder.superheroCharacterName.text = marvelSuperhero.superheroCharacterName
            holder.superheroDescription.text = marvelSuperhero.superheroDescription

            //Loading image using Picasso
            Picasso.get().load(marvelSuperhero.superheroImage).into(holder.superheroImage)

            holder.itemView.setOnClickListener { listener(marvelSuperhero) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val superheroImage: ImageView = itemView.findViewById(R.id.superhero_image)
        val superheroName: TextView = itemView.findViewById(R.id.superhero_name)
        val superheroCharacterName: TextView = itemView.findViewById(R.id.superhero_character_name)
        val superheroDescription: TextView = itemView.findViewById(R.id.superhero_description)

    }
}