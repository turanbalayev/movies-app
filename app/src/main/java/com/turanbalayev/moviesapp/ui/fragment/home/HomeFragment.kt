package com.turanbalayev.moviesapp.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.turanbalayev.moviesapp.databinding.FragmentHomeBinding
import com.turanbalayev.moviesapp.model.Movie
import com.turanbalayev.moviesapp.model.MovieResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeTenMoviesAdapter()
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = viewModel.isAuth()

        if (!result) {
            goToLogin()
        }


        viewModel.getMovies()
        setRecyclerView()
        observeAll()
        listenToButtons()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView() {

        // RecyclerView 1
        binding.rvTopTenMovies.adapter = adapter
        binding.rvTopTenMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        // RecyclerView 2
        binding.rvNewReleases.adapter = adapter
        binding.rvNewReleases.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }


    private fun observeAll() {
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it.results)
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it == true){
                if(adapter.differ.currentList.isEmpty()) {
                    binding.homeProgressBar.visibility = View.VISIBLE
                }
            } else {
                binding.homeProgressBar.visibility = View.GONE
            }
        }


    }

    private fun listenToButtons() {
        binding.textViewSeeAll.setOnClickListener {
            goToTopMovies()
        }

        binding.imgIcSearch.setOnClickListener {
            gotoExplore()
        }


    }


    private fun goToTopMovies() {
        val action = HomeFragmentDirections.actionHomeFragmentToTopMoviesFragment()
        findNavController().navigate(action)
    }

    private fun goToLogin() {
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)

    }

    private fun gotoExplore() {
        val action = HomeFragmentDirections.actionHomeFragmentToExploreFragment()
        findNavController().navigate(action)
    }

}