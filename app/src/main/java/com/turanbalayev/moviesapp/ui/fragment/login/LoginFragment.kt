package com.turanbalayev.moviesapp.ui.fragment.login

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
import com.turanbalayev.moviesapp.databinding.FragmentLoginBinding
import com.turanbalayev.moviesapp.ui.fragment.login.LoginFragmentDirections
import com.turanbalayev.moviesapp.util.Validation.Companion.validateEmail
import com.turanbalayev.moviesapp.util.Validation.Companion.validatePassword
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment() : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAll()
        listenToButtons()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun gotoCreateAccount() {
        val action = LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment()
        findNavController().navigate(action)
    }

    private fun loginYourAccount() {

        binding.outlinedTextFieldEmail.error = null
        binding.outlinedTextFieldPassword.error = null

        val email = binding.textViewOutlinedEmail.text.toString().trim()
        val password = binding.textViewOutlinedPassword.text.toString().trim()


        val emailResult = validateEmail(email)
        if (emailResult.hasError) {
            binding.outlinedTextFieldEmail.error = emailResult.message
        }

        val passwordResult = validatePassword(password)
        if (passwordResult.hasError) {
            binding.outlinedTextFieldPassword.error = passwordResult.message
        }


        if (binding.outlinedTextFieldEmail.error == null && binding.outlinedTextFieldPassword.error == null) {
            viewModel.loginUser(email,password)
            gotoHome()
        }
    }

    private fun listenToButtons(){
        binding.textViewSignUpBottom.setOnClickListener {
            gotoCreateAccount()
        }

        binding.buttonSignIn.setOnClickListener {
            loginYourAccount()
        }
    }

    private fun observeAll(){

        viewModel.authResult.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
        }
    }

    private fun gotoHome(){
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }


}