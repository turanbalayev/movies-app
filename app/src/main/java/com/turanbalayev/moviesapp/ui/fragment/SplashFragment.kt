package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.turanbalayev.moviesapp.databinding.FragmentSplashBinding
import com.turanbalayev.moviesapp.ui.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {


    override fun onViewCreateFinished() {
        lifecycleScope.launch {
            delay(4000)
            gotoWelcome()
        }
    }

    override fun setup() {

    }

    private fun gotoWelcome() {
        val action = SplashFragmentDirections.actionSplashFragmentToWelcomeFragment()
        findNavController().navigate(action)
    }


}