package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turanbalayev.moviesapp.databinding.FragmentLetsyouinBinding
import dagger.hilt.android.AndroidEntryPoint


class LetsyouinFragment : Fragment() {
    private var _binding: FragmentLetsyouinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetsyouinBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listenToButtons()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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