package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.turanbalayev.moviesapp.databinding.FragmentLoginBinding
import com.turanbalayev.moviesapp.util.CustomError
import com.turanbalayev.moviesapp.util.Validation.Companion.validateEmail
import com.turanbalayev.moviesapp.util.Validation.Companion.validatePassword


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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

        binding.textViewSignUpBottom.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment()
            findNavController().navigate(action)
        }


        binding.buttonSignIn.setOnClickListener {
            binding.outlinedTextFieldEmail.error = null
            binding.outlinedTextFieldPassword.error = null

            val email = binding.textViewOutlinedEmail.text.toString().trim()
            val password = binding.textViewOutlinedPassword.text.toString().trim()


            val emailResult = validateEmail(email)
            if (emailResult.hasError) {
                binding.outlinedTextFieldEmail.error = emailResult.message
            }

            val passwordResult = validatePassword(password)
            if(passwordResult.hasError){
                binding.outlinedTextFieldPassword.error = passwordResult.message
            }


            if (binding.outlinedTextFieldEmail.error == null && binding.outlinedTextFieldPassword.error == null) {
                Toast.makeText(requireContext(), "Everything is good.", Toast.LENGTH_LONG).show()
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}