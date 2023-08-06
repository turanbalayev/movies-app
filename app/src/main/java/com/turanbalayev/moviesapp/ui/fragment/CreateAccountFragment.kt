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
import com.turanbalayev.moviesapp.databinding.FragmentCreateAccountBinding
import com.turanbalayev.moviesapp.util.Validation


class CreateAccountFragment : Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.textViewSignIn.setOnClickListener {
            val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        binding.buttonSignUp.setOnClickListener {
            binding.outlinedTextFieldEmail.error = null
            binding.outlinedTextFieldPassword.error = null

            val email = binding.textViewOutlinedEmail.text.toString().trim()
            val password = binding.textViewOutlinedPassword.text.toString().trim()


            val emailResult = Validation.validateEmail(email)
            if (emailResult.hasError) {
                binding.outlinedTextFieldEmail.error = emailResult.message
            }

            val passwordResult = Validation.validatePassword(password)
            if(passwordResult.hasError){
                binding.outlinedTextFieldPassword.error = passwordResult.message
            }


            if (binding.outlinedTextFieldEmail.error == null && binding.outlinedTextFieldPassword.error == null) {
                Toast.makeText(requireContext(), "Everything is good.", Toast.LENGTH_LONG).show()

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(requireContext(),"You signed up successfully!",Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(requireContext(),"Operation failed! Try again.",Toast.LENGTH_SHORT).show()
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