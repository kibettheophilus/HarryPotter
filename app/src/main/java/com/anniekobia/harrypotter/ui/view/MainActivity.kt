package com.anniekobia.harrypotter.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.anniekobia.harrypotter.BuildConfig
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.databinding.ActivityMainBinding
import timber.log.Timber
import timber.log.Timber.DebugTree


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Disable Logging and debugging in release
         */
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        /**
         * Set navigation component with toolbar
         */
        val navController = findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}