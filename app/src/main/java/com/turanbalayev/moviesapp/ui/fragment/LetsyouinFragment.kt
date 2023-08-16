package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turanbalayev.moviesapp.databinding.FragmentLetsyouinBinding
import com.turanbalayev.moviesapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


class LetsyouinFragment : BaseFragment<FragmentLetsyouinBinding>(FragmentLetsyouinBinding::inflate) {




    override fun onViewCreateFinished() {
        listenToButtons()
    }

    override fun setup() {

    }

    private fun listenToButtons(){

        binding.buttonSignInWithPass.setOnClickListener {
            gotoLogin()
        }

        binding.textViewSignUp.setOnClickListener {
            gotoCreateAccount()
        }
    }

    private fun gotoCreateAccount(){
        val action = LetsyouinFragmentDirections.actionLetsyouinFragmentToCreateAccountFragment()
        findNavController().navigate(action)
    }

    private fun gotoLogin(){
        val action = LetsyouinFragmentDirections.actionLetsyouinFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}