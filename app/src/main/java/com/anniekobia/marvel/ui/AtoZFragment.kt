package com.anniekobia.marvel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.R
import com.anniekobia.marvel.adapters.MarvelSuperheroDataAdapter
import com.anniekobia.marvel.utils.MarvelSuperheroDummyData
import com.anniekobia.marvel.utils.MarvelSuperheroDummyDataClass


/**
 * A simple [Fragment] subclass.
 */
class AtoZFragment : Fragment() {

    var marvelSuperheroesListDataClass: ArrayList<MarvelSuperheroDummyDataClass> =
        MarvelSuperheroDummyData.MARVEL_SUPERHEROES_LIST_DATA_CLASS

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_atoz, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerViewAdapter = MarvelSuperheroDataAdapter(marvelSuperheroesListDataClass) {
            view.findNavController().navigate(R.id.global_detailsFragment)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        return view
    }
}
