package com.anniekobia.harrypotter.ui.adapter

import android.os.Build
import android.util.Log
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
        private val listener: (Character, ImageView) -> Unit
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

        holder.characterName.text = character.name
        holder.characterActorName.text = character.actor

        holder.characterImage.apply {
            transitionName = character.image
            Log.e("TransFirst: ", character.image)
            //Loading image using Picasso
            Picasso.get().load(character.image).into(holder.characterImage)
        }

        holder.itemView.setOnClickListener { listener(character,holder.characterImage) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage: ImageView = itemView.findViewById(R.id.character_image)
        val characterName: TextView = itemView.findViewById(R.id.character_name)
        val characterActorName: TextView = itemView.findViewById(R.id.character_actor_name)
    }
}