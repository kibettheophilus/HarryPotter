package com.anniekobia.marvel.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.marvel.ui.adapter.MarvelSuperheroDataAdapter

import com.anniekobia.marvel.R

/**
 * A simple [Fragment] subclass.
 */
class LastViewedFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MarvelSuperheroDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_last_viewed, container, false)


        return view
    }


}
