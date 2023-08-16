package com.turanbalayev.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)


        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.downloadFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.homeFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.exploreFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.myListFragment -> binding.bottomNav.visibility = View.VISIBLE
                R.id.profileFragment -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.GONE

            }
        }

    }
}