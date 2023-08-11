package com.turanbalayev.moviesapp.ui.fragment.topmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.FragmentTopMoviesBinding
import com.turanbalayev.moviesapp.model.Movie
import com.turanbalayev.moviesapp.ui.fragment.home.HomeTenMoviesAdapter


class TopMoviesFragment : Fragment() {
    private var _binding:FragmentTopMoviesBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeTenMoviesAdapter()

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
        setRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(){
        val movie1 = Movie(1,"image1",9.8)
        val movie2 = Movie(2,"image2",8.0)
        val movie3 = Movie(3,"image3",7.5)
        val movie4 = Movie(4,"image4",5.6)
        val movie5 = Movie(5,"image5",8.5)
        val movie6 = Movie(6,"image6",3.0)
        val movie7 = Movie(6,"image6",6.5)
        val movie8 = Movie(6,"image6",7.0)
        val movie9 = Movie(6,"image6",8.0)
        val movie10 = Movie(6,"image6",9.0)

        // Shared Adapter
        adapter.differ.submitList(listOf(
            movie1,movie2,movie3,movie4,movie5,movie6,movie7,movie8,movie9,movie10
        ))

        binding.rvTopMovies.adapter = adapter
        binding.rvTopMovies.layoutManager = GridLayoutManager(requireContext(),2)
    }
}