package com.example.marvel.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.Data.MarvelSuperheroDataClassDummy
import com.example.marvel.R


class MarvelSuperheroDataAdapter(private val context: Context?, val characterlist: ArrayList<MarvelSuperheroDataClassDummy>) :
    RecyclerView.Adapter<MarvelSuperheroDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characterlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val marvelSuperhero = characterlist[position]
        holder.superheroName.setText(marvelSuperhero.superheroName)
        holder.superheroCharacterName.setText(marvelSuperhero.superheroCharacterName)
        holder.superheroDescription.setText(marvelSuperhero.superheroDescription)
        holder.superheroImage.setImageResource(R.drawable.blackpanther)

        holder.moreInfo.setOnClickListener {
            Toast.makeText(context,"View Character Details", Toast.LENGTH_LONG).show()
        }
        holder.moreInfoArrow.setOnClickListener {
            Toast.makeText(context,"View Character Details", Toast.LENGTH_LONG).show()
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val superheroImage: ImageView = itemView.findViewById(R.id.superhero_image)
        val superheroName: TextView = itemView.findViewById(R.id.superhero_name)
        val superheroCharacterName: TextView = itemView.findViewById(R.id.superhero_character_name)
        val superheroDescription: TextView = itemView.findViewById(R.id.superhero_description)

        val moreInfo : TextView = itemView.findViewById(R.id.more_info)
        val moreInfoArrow : ImageView = itemView.findViewById(R.id.more_info_arrow)

    }

}