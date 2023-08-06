package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.turanbalayev.moviesapp.databinding.FragmentLoginBinding
import com.turanbalayev.moviesapp.util.CustomError
import com.turanbalayev.moviesapp.util.Validation.Companion.validateEmail
import com.turanbalayev.moviesapp.util.Validation.Companion.validatePassword


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

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
        auth = Firebase.auth

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
            if (passwordResult.hasError) {
                binding.outlinedTextFieldPassword.error = passwordResult.message
            }


            if (binding.outlinedTextFieldEmail.error == null && binding.outlinedTextFieldPassword.error == null) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "You signed in successfully.",
                            Toast.LENGTH_LONG
                        ).show()
                    }else {
                        Toast.makeText(requireContext(), "Something went wrong.", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}