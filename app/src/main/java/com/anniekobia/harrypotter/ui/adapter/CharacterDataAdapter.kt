package com.anniekobia.harrypotter.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.databinding.CharacterItemRowBinding
import com.squareup.picasso.Picasso


class CharacterDataAdapter(
        private var characterList: ArrayList<Character>,
        private val listener: (Character, ImageView) -> Unit
) : RecyclerView.Adapter<CharacterDataAdapter.MyViewHolder>() {

    private lateinit var binding: CharacterItemRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CharacterItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characterList[position]

        holder.characterName.text = character.name
        holder.characterActorName.text = character.actor

        holder.characterImage.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                transitionName = character.image
            }
            //Loading image using Picasso
            Picasso.get().load(character.image).into(holder.characterImage)
        }

        holder.itemView.setOnClickListener { listener(character,holder.characterImage) }
    }

    class MyViewHolder(itemBinding: CharacterItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        val characterImage: ImageView = itemBinding.characterImage
        val characterName: TextView = itemBinding.characterName
        val characterActorName: TextView = itemBinding.characterActorName
    }
}