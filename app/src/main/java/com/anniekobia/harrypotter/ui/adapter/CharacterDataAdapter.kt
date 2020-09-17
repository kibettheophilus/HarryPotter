package com.anniekobia.harrypotter.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.databinding.CharacterItemRowBinding
import com.anniekobia.harrypotter.utils.loadImage


class CharacterDataAdapter(
    private var characterList: ArrayList<Character>,
    private val listener: (Character, ImageView) -> Unit
) : RecyclerView.Adapter<CharacterDataAdapter.MyViewHolder>() {

    private lateinit var binding: CharacterItemRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            CharacterItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = characterList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    class MyViewHolder(
        itemBinding: CharacterItemRowBinding,
        private val listener: (Character, ImageView) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val characterImage: ImageView = itemBinding.characterImage
        private val characterName: TextView = itemBinding.characterName
        private val characterActorName: TextView = itemBinding.characterActorName


        fun bind(character: Character) {
            with(character) {
                characterName.text = name
                characterActorName.text = actor
                characterImage.apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        transitionName = character.image
                    }
//                    Picasso.get().load(image).into(characterImage)
                    loadImage(image, characterImage)
                    itemView.setOnClickListener { listener(character, characterImage) }
                }
            }
        }
    }
}