package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.data.api.model.Character
import com.anniekobia.harrypotter.ui.adapter.CharacterDataAdapter
import com.anniekobia.harrypotter.ui.viewmodel.CharacterViewModel


/**
 * A simple [Fragment] subclass.
 */
class StudentsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var recyclerViewAdapter: CharacterDataAdapter
    private val characterViewModel: CharacterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_student, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = GONE
        setRecyclerView(view)

        return view
    }

    private fun setRecyclerView(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        progressBar.visibility = VISIBLE
        characterViewModel.getStudentCharacters().observe(viewLifecycleOwner,
            Observer<ArrayList<Character>> { characters ->
                if (characters != null) {
                    recyclerViewAdapter =
                        CharacterDataAdapter(characters) {
                            val bundle = bundleOf("Character" to it)
                            view.findNavController().navigate(R.id.global_detailsFragment, bundle)
                        }
                    recyclerView.adapter = recyclerViewAdapter
                    progressBar.visibility = GONE
                }
            })
    }
}
