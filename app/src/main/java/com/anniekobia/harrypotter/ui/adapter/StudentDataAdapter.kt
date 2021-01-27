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
import com.anniekobia.harrypotter.databinding.StudentItemRowBinding
import com.anniekobia.harrypotter.utils.loadUrl


class StudentDataAdapter(
    private val listener: (Character, ImageView) -> Unit
) : ListAdapter<Character,StudentDataAdapter.MyViewHolder>(CharacterDiffer) {

    private lateinit var binding: StudentItemRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            StudentItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class MyViewHolder(
        itemBinding: StudentItemRowBinding,
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
                    characterImage.loadUrl(image)
                    itemView.setOnClickListener { listener(character, characterImage) }
                }
            }
        }
    }

    companion object CharacterDiffer : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean = oldItem == newItem
    }
}