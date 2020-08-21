package com.anniekobia.marvel.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.R
import com.anniekobia.marvel.data.api.model.MarvelSuperhero
import com.anniekobia.marvel.ui.adapter.MarvelSuperheroDataAdapter
import com.anniekobia.marvel.ui.viewmodel.MarvelHeroViewModel


/**
 * A simple [Fragment] subclass.
 */
class AtoZFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter
    lateinit var marvelHeroViewModel: MarvelHeroViewModel
    lateinit var completeProfiles: ArrayList<MarvelSuperhero>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_atoz, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = GONE
        setRecyclerView(view)

        return view
    }

    private fun setRecyclerView(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        marvelHeroViewModel =
            ViewModelProvider(this).get<MarvelHeroViewModel>(MarvelHeroViewModel::class.java)
        marvelHeroViewModel.init()
        marvelHeroViewModel.searchMarvelHero("name", 100)
        progressBar.visibility = VISIBLE
        marvelHeroViewModel.getMarvelHeroLiveData()?.observe(viewLifecycleOwner,
            Observer<ArrayList<MarvelSuperhero>?> { marvelSuperheros ->
                if (marvelSuperheros != null) {
                    val heros = filterResults(marvelSuperheros).distinct() as ArrayList
                    recyclerViewAdapter =
                        MarvelSuperheroDataAdapter(heros) {
                            val bundle = bundleOf("Superhero" to it)
                            view.findNavController().navigate(R.id.global_detailsFragment, bundle)
                        }
                    recyclerView.adapter = recyclerViewAdapter
                    progressBar.visibility = GONE
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
