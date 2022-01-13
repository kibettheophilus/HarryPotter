package com.anniekobia.harrypotter.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.harrypotter.data.remote.model.Character
import com.anniekobia.harrypotter.data.remote.model.CharacterTwoListItem
import com.anniekobia.harrypotter.databinding.CharacterItemRowBinding
import com.anniekobia.harrypotter.utils.loadUrl


class CharacterDataAdapter(
    private val listener: (CharacterTwoListItem, ImageView) -> Unit
) : ListAdapter<CharacterTwoListItem,CharacterDataAdapter.MyViewHolder>(CharacterDiffer) {

    private lateinit var binding: CharacterItemRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            CharacterItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MyViewHolder(
        itemBinding: CharacterItemRowBinding,
        private val listener: (CharacterTwoListItem, ImageView) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val characterImage: ImageView = itemBinding.characterImage
        private val characterName: TextView = itemBinding.characterName
        private val characterActorName: TextView = itemBinding.characterActorName


        fun bind(character: CharacterTwoListItem) {
            with(character) {
                characterName.text = name
                characterActorName.text = actor
                characterImage.apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        transitionName = character.image
                    }
                    characterImage.loadUrl(image)
                    itemView.setOnClickListener { listener(character, characterImage) }
                }
            }
        }
    }

    companion object CharacterDiffer : DiffUtil.ItemCallback<CharacterTwoListItem>() {
        override fun areItemsTheSame(
            oldItem: CharacterTwoListItem,
            newItem: CharacterTwoListItem
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: CharacterTwoListItem,
            newItem: CharacterTwoListItem
        ): Boolean = oldItem == newItem
    }
}