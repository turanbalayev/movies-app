package com.turanbalayev.moviesapp.ui.fragment.topmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.FragmentTopMoviesBinding
import com.turanbalayev.moviesapp.model.Movie
import com.turanbalayev.moviesapp.ui.fragment.home.HomeTenMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopMoviesFragment : Fragment() {
    private var _binding:FragmentTopMoviesBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeTenMoviesAdapter()
    private val viewModel: TopMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
        setRecyclerView()
        observerAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(){
        binding.rvTopMovies.adapter = adapter
        binding.rvTopMovies.layoutManager = GridLayoutManager(requireContext(),2)
    }


    private fun observerAll(){
        viewModel.data.observe(viewLifecycleOwner){
            adapter.differ.submitList(it.results)
        }
    }
}