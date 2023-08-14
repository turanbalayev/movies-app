package com.turanbalayev.moviesapp.ui.fragment.explore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.turanbalayev.moviesapp.databinding.FragmentExploreBinding
import com.turanbalayev.moviesapp.ui.fragment.home.HomeTenMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExploreViewModel by viewModels()
    private val homeTenMoviesAdapter = HomeTenMoviesAdapter()
    private val exploreSearchAdapter = ExploreSearchAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()

        setUpRecyclerView()
        observeAll()
        listenToButtons()


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        binding.rvExplore.adapter = homeTenMoviesAdapter
        binding.rvExploreSecond.adapter = exploreSearchAdapter
    }

    private fun observeAll() {
        viewModel.data.observe(viewLifecycleOwner) {
            homeTenMoviesAdapter.differ.submitList(it.results)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it == true) {
                if (homeTenMoviesAdapter.differ.currentList.isEmpty()) {
                    binding.exploreProgressBar.visibility = View.VISIBLE
                }
            } else {
                binding.exploreProgressBar.visibility = View.GONE
            }
        }

        viewModel.searched_movies.observe(viewLifecycleOwner) {
            exploreSearchAdapter.differ.submitList(it)
        }

        viewModel.has404.observe(viewLifecycleOwner){
            if(it == true){
                viewModel.clearSearchedMovies()
                binding.rvExplore.visibility = View.GONE
                binding.rvExploreSecond.visibility = View.GONE
                binding.textViewTopSearches.visibility = View.GONE
                binding.constraintLayout404.visibility = View.VISIBLE
            } else{
                binding.constraintLayout404.visibility = View.GONE
            }
        }

    }

    private fun listenToButtons() {


        binding.textViewOutlinedSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(search_query: Editable?) {
                search_query?.let {
                    if (it.length >= 2) {
                        binding.rvExplore.visibility = View.GONE
                        binding.rvExploreSecond.visibility = View.VISIBLE
                        binding.textViewTopSearches.visibility = View.VISIBLE
                        viewModel.searchMoviesByQuery(search_query.toString().lowercase())
                    } else {
                        binding.rvExplore.visibility = View.VISIBLE
                        binding.rvExploreSecond.visibility = View.GONE
                        binding.textViewTopSearches.visibility = View.GONE
                    }
                }


            }

        })


    }
}