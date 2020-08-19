package com.anniekobia.marvel.ui.view

import android.os.Bundle
import android.util.Log
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
import com.anniekobia.marvel.data.api.model.marvelapi.Marvelhero
import com.anniekobia.marvel.data.api.model.marvelapi.Result
import com.anniekobia.marvel.ui.adapter.MarvelSuperheroDataAdapter
import com.anniekobia.marvel.ui.viewmodel.MarvelHeroViewModel


/**
 * A simple [Fragment] subclass.
 */
class AtoZFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter
    lateinit var marvelHeroViewModel: MarvelHeroViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_atoz, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        setRecyclerView(view)

        return view
    }

    private fun setRecyclerView(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        marvelHeroViewModel =
            ViewModelProvider(this).get<MarvelHeroViewModel>(MarvelHeroViewModel::class.java)
        marvelHeroViewModel.init()
        marvelHeroViewModel.searchMarvelHero("name", 50)
        marvelHeroViewModel.getMarvelHeroLiveData()?.observe(viewLifecycleOwner,
            Observer<Marvelhero?> { marvelHero ->
                if (marvelHero != null) {
//                    val heros = filterResults(marvelHero)
                    recyclerViewAdapter =
                        MarvelSuperheroDataAdapter(marvelHero.data.results as ArrayList<Result>) {
                            val bundle = bundleOf("Superhero" to it)
                            view.findNavController().navigate(R.id.global_detailsFragment, bundle)
                        }
                    recyclerView.adapter = recyclerViewAdapter
                }
            })
    }

    private fun filterResults(marvelhero: Marvelhero): Marvelhero{
        var incompleteProfiles: ArrayList<Result>? = null
        for (hero in (marvelhero.data.results)){
            val imageUnavailable = "image_not_available"
            if (imageUnavailable in hero.thumbnail.path){
                Log.e("Herenow2",hero.name)
                incompleteProfiles?.add(hero)
            }
        }
        (marvelhero.data.results as ArrayList).removeAll(incompleteProfiles as ArrayList)
        return marvelhero
    }
}
