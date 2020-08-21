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
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
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
    lateinit var completeProfiles: ArrayList<MarvelSuperhero>


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
        marvelHeroViewModel.searchMarvelHero("name", 100)
        marvelHeroViewModel.getMarvelHeroLiveData()?.observe(viewLifecycleOwner,
            Observer<ArrayList<MarvelSuperhero>?> { marvelSuperheros ->
                if (marvelSuperheros != null) {
                    val heros = filterResults(marvelSuperheros)
                    recyclerViewAdapter =
                        MarvelSuperheroDataAdapter(heros) {
                            val bundle = bundleOf("Superhero" to it)
                            view.findNavController().navigate(R.id.global_detailsFragment, bundle)
                        }
                    recyclerView.adapter = recyclerViewAdapter
                }
            })
    }

    private fun filterResults(marvelheros: ArrayList<MarvelSuperhero>): ArrayList<MarvelSuperhero> {
        completeProfiles = arrayListOf()
        for (hero in marvelheros){
            val imageUnavailable = "image_not_available"
            if (imageUnavailable in hero.superheroImage){
                Log.e("No image for: ",hero.superheroCharacterName)
            }else{
                completeProfiles.add(hero)
            }
        }
        return completeProfiles
    }
}
