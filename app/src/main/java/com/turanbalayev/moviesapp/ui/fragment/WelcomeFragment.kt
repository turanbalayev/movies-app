package com.turanbalayev.moviesapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turanbalayev.moviesapp.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
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