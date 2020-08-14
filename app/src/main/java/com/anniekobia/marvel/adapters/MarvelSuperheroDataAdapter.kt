package com.anniekobia.marvel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.utils.MarvelSuperheroDummyDataClass
import com.anniekobia.marvel.R


class MarvelSuperheroDataAdapter(
    private val characterList: ArrayList<MarvelSuperheroDummyDataClass>,
    private val listener: (MarvelSuperheroDummyDataClass) -> Unit
) : RecyclerView.Adapter<MarvelSuperheroDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val marvelSuperhero = characterList[position]
        holder.bind(marvelSuperhero)
        holder.itemView.setOnClickListener{listener(marvelSuperhero)}
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val superheroImage: ImageView = itemView.findViewById(R.id.superhero_image)
        private val superheroName: TextView = itemView.findViewById(R.id.superhero_name)
        private val superheroCharacterName: TextView = itemView.findViewById(R.id.superhero_character_name)
        private val superheroDescription: TextView = itemView.findViewById(R.id.superhero_description)

        fun bind(item: MarvelSuperheroDummyDataClass) {
            superheroName.text = item.superheroName
            superheroCharacterName.text = item.superheroCharacterName
            superheroDescription.text = item.superheroDescription
            superheroImage.setImageResource(item.superheroImage)
        }
    }

}