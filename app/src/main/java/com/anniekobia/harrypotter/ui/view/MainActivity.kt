package com.anniekobia.harrypotter.ui.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.BuildConfig
import com.anniekobia.harrypotter.R
import com.anniekobia.harrypotter.databinding.ActivityMainBinding
import timber.log.Timber
import timber.log.Timber.DebugTree
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /**
     * Global variable isNetworkConnected to check for network connectivity
     */
    object Variables {
        var isNetworkConnected: Boolean by Delegates.observable(false) { property, oldValue, newValue -> }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startNetworkCallback()
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

    /**
     * Checks for network connection state
     */
    fun startNetworkCallback() {
        val cm: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Variables.isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    Variables.isNetworkConnected = false
                }
            })
    }

}