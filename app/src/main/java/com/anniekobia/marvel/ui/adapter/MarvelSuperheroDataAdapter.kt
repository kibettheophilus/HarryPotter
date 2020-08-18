package com.anniekobia.marvel.ui.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.R
import com.anniekobia.marvel.data.api.marvelapi.Result
import java.io.File


class MarvelSuperheroDataAdapter(
    private var characterList: ArrayList<Result>,
    private val listener: (Result) -> Unit
) : RecyclerView.Adapter<MarvelSuperheroDataAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun setResults(results: ArrayList<Result>) {
        characterList = results
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val marvelSuperhero = characterList[position]

        holder.superheroName.text = marvelSuperhero.name
        holder.superheroCharacterName.text = marvelSuperhero.name
        holder.superheroDescription.text = marvelSuperhero.description

        val photo = File(marvelSuperhero.thumbnail.path + marvelSuperhero.thumbnail.extension)
        val imageUri: Uri = Uri.fromFile(photo)
        holder.superheroImage.setImageURI(imageUri)

//        holder.bind(marvelSuperhero)
        holder.itemView.setOnClickListener { listener(marvelSuperhero) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val superheroImage: ImageView = itemView.findViewById(R.id.superhero_image)
        val superheroName: TextView = itemView.findViewById(R.id.superhero_name)
        val superheroCharacterName: TextView = itemView.findViewById(R.id.superhero_character_name)
        val superheroDescription: TextView = itemView.findViewById(R.id.superhero_description)

//        fun bind(item: MarvelSuperheroDummyDataClass) {
//            superheroName.text = item.superheroName
//            superheroCharacterName.text = item.superheroCharacterName
//            superheroDescription.text = item.superheroDescription
//            superheroImage.setImageResource(item.superheroImage)
//        }

//        fun bind(item: Result) {
//            superheroName.text = item.data.results[0].name
//            superheroCharacterName.text = item.data.results[0].name
//            superheroDescription.text = item.data.results[0].description
//
//            val photo = File(item.data.results[0].thumbnail.path+item.data.results[0].thumbnail.extension)
//            val imageUri: Uri = Uri.fromFile(photo)
//            superheroImage.setImageURI(imageUri)
////                setImageResource(item.data.results[0].thumbnail.path+item.data.results[0].thumbnail.extension)
//        }
    }
}