package com.anniekobia.marvel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.adapters.MarvelSuperheroDataAdapter
import com.anniekobia.marvel.utils.MarvelSuperheroDummyDataClass

import com.anniekobia.marvel.R
import com.anniekobia.marvel.utils.MarvelSuperheroDummyData

/**
 * A simple [Fragment] subclass.
 */
class FragmentLastViewed : Fragment() {

    var marvelSuperheroesListDataClass: ArrayList<MarvelSuperheroDummyDataClass> =
        MarvelSuperheroDummyData.MARVEL_SUPERHEROES_LIST_DATA_CLASS

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_last_viewed, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerViewAdapter = MarvelSuperheroDataAdapter(marvelSuperheroesListDataClass) {
            Toast.makeText(context, "Open activity with details", Toast.LENGTH_LONG).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        return view
    }
}
