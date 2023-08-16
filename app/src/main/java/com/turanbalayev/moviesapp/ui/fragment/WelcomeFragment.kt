package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turanbalayev.moviesapp.databinding.FragmentWelcomeBinding
import com.turanbalayev.moviesapp.ui.base.BaseFragment


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {


    override fun onViewCreateFinished() {
        listenToButtons()
    }

    override fun setup() {

    }

    private fun listenToButtons() {
        binding.buttonGetStarted.setOnClickListener {
            gotoLetsyouin()
        }
    }

    private fun gotoLetsyouin() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToLetsyouinFragment()
        findNavController().navigate(action)
    }

}