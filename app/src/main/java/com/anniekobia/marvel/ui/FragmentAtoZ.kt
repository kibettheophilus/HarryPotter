package com.anniekobia.marvel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.utils.MarvelSupeheroDummyData
import com.anniekobia.marvel.adapters.MarvelSuperheroDataAdapter

import com.anniekobia.marvel.R
import com.anniekobia.marvel.data.MarvelSuperheroDataClassDummy

/**
 * A simple [Fragment] subclass.
 */
class FragmentAtoZ : Fragment() {

    var marvelSuperheroesList: ArrayList<MarvelSuperheroDataClassDummy> =
        MarvelSupeheroDummyData.marvelSuperheroesList

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_atoz, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerViewAdapter = MarvelSuperheroDataAdapter(marvelSuperheroesList) {
                item -> Toast.makeText(context, "Open activity with details", Toast.LENGTH_LONG).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        return view
    }
}
