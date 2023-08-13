package com.turanbalayev.moviesapp.ui.fragment.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.turanbalayev.moviesapp.R
import com.turanbalayev.moviesapp.databinding.FragmentExploreBinding
import com.turanbalayev.moviesapp.ui.fragment.home.HomeTenMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private var _binding:FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private val viewModel:ExploreViewModel by viewModels()
    private val adapter = HomeTenMoviesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()

        setUpRecyclerView()
        observeAll()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){
        binding.rvExplore.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvExplore.adapter = adapter
    }

    private fun observeAll(){
        viewModel.data.observe(viewLifecycleOwner){
            adapter.differ.submitList(it.results)
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if(it == true){
                if(adapter.differ.currentList.isEmpty()){
                binding.exploreProgressBar.visibility = View.VISIBLE}
            }else {
                binding.exploreProgressBar.visibility = View.GONE
            }
        }

    }
}