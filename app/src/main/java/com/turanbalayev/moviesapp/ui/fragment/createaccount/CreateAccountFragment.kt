package com.turanbalayev.moviesapp.ui.fragment.createaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.turanbalayev.moviesapp.databinding.FragmentCreateAccountBinding
import com.turanbalayev.moviesapp.ui.base.BaseFragment
import com.turanbalayev.moviesapp.ui.fragment.createaccount.CreateAccountFragmentDirections
import com.turanbalayev.moviesapp.util.Validation
import dagger.hilt.android.AndroidEntryPoint





@AndroidEntryPoint
class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>(FragmentCreateAccountBinding::inflate) {

    private val viewModel: CreateAccountViewModel by viewModels()

    private fun goToLoginFragment() {
        val action =
            CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun createAccount() {
        binding.outlinedTextFieldEmail.error = null
        binding.outlinedTextFieldPassword.error = null

        val email = binding.textViewOutlinedEmail.text.toString().trim()
        val password = binding.textViewOutlinedPassword.text.toString().trim()


        val emailResult = Validation.validateEmail(email)
        if (emailResult.hasError) {
            binding.outlinedTextFieldEmail.error = emailResult.message
        }

        val passwordResult = Validation.validatePassword(password)
        if (passwordResult.hasError) {
            binding.outlinedTextFieldPassword.error = passwordResult.message
        }


        if (binding.outlinedTextFieldEmail.error == null && binding.outlinedTextFieldPassword.error == null) {
            viewModel.registerUser(email, password)
        }
    }

    private fun observeAll(){
        /*viewModel.authResultStr.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
        }

        viewModel.hasRegistered.observe(viewLifecycleOwner){
            if(it == true) {
                goToLoginFragment()
            }
        }*/

        viewModel.authResult.observe(viewLifecycleOwner){
            if(it.hasRegistered){
                goToLoginFragment()
            }
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun listenToButtons(){
        binding.textViewSignIn.setOnClickListener {
            goToLoginFragment()
        }

        binding.buttonSignUp.setOnClickListener {
            createAccount()
        }
    }

    override fun onViewCreateFinished() {
        observeAll()
        listenToButtons()
    }

    override fun setup() {

    }


}