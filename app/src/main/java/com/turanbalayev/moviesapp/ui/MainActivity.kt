package com.turanbalayev.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}