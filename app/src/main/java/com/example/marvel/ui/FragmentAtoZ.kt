package com.example.marvel.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.utils.MarvelSupeheroDummyData
import com.example.marvel.adapters.MarvelSuperheroDataAdapter

import com.example.marvel.R
import com.example.marvel.data.MarvelSuperheroDataClassDummy

/**
 * A simple [Fragment] subclass.
 */
class FragmentAtoZ : Fragment() {

    var marvelSuperheroesList : ArrayList<MarvelSuperheroDataClassDummy>  = MarvelSupeheroDummyData.marvelSuperheroesList

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_atoz, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerViewAdapter = MarvelSuperheroDataAdapter(context,marvelSuperheroesList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter


        return view
    }

}
