package com.anniekobia.marvel.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.R
import com.anniekobia.marvel.data.api.marvelapi.Marvelhero
import com.anniekobia.marvel.data.api.marvelapi.Result
import com.anniekobia.marvel.ui.adapter.MarvelSuperheroDataAdapter
import com.anniekobia.marvel.ui.viewmodel.MarvelHeroViewModel


/**
 * A simple [Fragment] subclass.
 */
class AtoZFragment : Fragment() {

//    var marvelSuperheroesListDataClass: ArrayList<MarvelSuperheroDummyDataClass> =
//        MarvelSuperheroDummyData.MARVEL_SUPERHEROES_LIST_DATA_CLASS

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter


    lateinit var marvelHeroViewModel: MarvelHeroViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_atoz, container, false)


        recyclerView = view.findViewById(R.id.recyclerView)
//        recyclerViewAdapter = MarvelSuperheroDataAdapter(marvelSuperheroesListDataClass) {
//            val bundle = bundleOf("Superhero" to it)
//            view.findNavController().navigate(R.id.global_detailsFragment, bundle)
//        }
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.adapter = recyclerViewAdapter

        marvelHeroViewModel = ViewModelProvider(this).get<MarvelHeroViewModel>(MarvelHeroViewModel::class.java)
        marvelHeroViewModel.init()
        marvelHeroViewModel.searchMarvelHero("name",50)
        marvelHeroViewModel.getMarvelHeroLiveData()?.observe(viewLifecycleOwner,
            Observer<Marvelhero?> { marvelHero ->
                if (marvelHero != null) {
                    recyclerViewAdapter = MarvelSuperheroDataAdapter(marvelHero.data.results as ArrayList<Result>) {
                        val bundle = bundleOf("Superhero" to it)
                        view.findNavController().navigate(R.id.global_detailsFragment, bundle)
                    }
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = recyclerViewAdapter
                }
            })

        return view
    }
}
