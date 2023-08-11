package com.turanbalayev.moviesapp.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        setRecyclerView()
        observeAll()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(){

        // RecyclerView 1
        binding.rvTopTenMovies.adapter = adapter
        binding.rvTopTenMovies.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        // RecyclerView 2
        binding.rvNewReleases.adapter = adapter
        binding.rvNewReleases.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }


    private fun observeAll(){
        viewModel.data.observe(viewLifecycleOwner){
            adapter.differ.submitList(it.results)
        }
    }

}