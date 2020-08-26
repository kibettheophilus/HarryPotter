package com.anniekobia.harrypotter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.api.model.Character
import com.squareup.picasso.Picasso


class CharacterDataAdapter(
        private var characterList: ArrayList<Character>,
        private val listener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterDataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characterList[position]

        holder.superheroName.text = character.name
        holder.superheroCharacterName.text = character.actor
        //Loading image using Picasso
        Picasso.get().load(character.image).into(holder.superheroImage)

        holder.itemView.setOnClickListener { listener(character) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val superheroImage: ImageView = itemView.findViewById(R.id.character_image)
        val superheroName: TextView = itemView.findViewById(R.id.character_name)
        val superheroCharacterName: TextView = itemView.findViewById(R.id.character_actor_name)
    }
}