package com.turanbalayev.moviesapp.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.turanbalayev.moviesapp.databinding.FragmentHomeBinding
import com.turanbalayev.moviesapp.model.Movie


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeTenMoviesAdapter()

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

        // Shared Adapter
        adapter.differ.submitList(listOf(
            movie1,movie2,movie3,movie4,movie5,movie6
        ))

        // RecyclerView 1
        binding.rvTopTenMovies.adapter = adapter
        binding.rvTopTenMovies.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        // RecyclerView 2
        binding.rvNewReleases.adapter = adapter
        binding.rvNewReleases.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

}