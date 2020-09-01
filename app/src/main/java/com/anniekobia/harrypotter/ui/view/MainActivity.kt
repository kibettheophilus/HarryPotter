package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.databinding.ActivityMainBinding
import com.anniekobia.harrypotter.ui.viewmodel.CharacterViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val characterViewModel: CharacterViewModel by viewModels()
    private var mboolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Load all characters data and save them to room db only once on application launch
        var settings = getSharedPreferences("LOAD_ALL_CHARACTERS_DATA", 0)
        mboolean = settings.getBoolean("FIRST_RUN", false)
        if (!mboolean) {
            //Call viewmodel method to load and save all characters
            characterViewModel.getAndSaveAllCharacters()
            settings = getSharedPreferences("LOAD_ALL_CHARACTERS_DATA", 0)
            val editor = settings.edit()
            editor.putBoolean("FIRST_RUN", true)
            editor.apply()
        }

        //Navigation component
        val navController = findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}