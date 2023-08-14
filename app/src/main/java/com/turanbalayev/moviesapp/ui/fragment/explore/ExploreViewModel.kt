package com.turanbalayev.moviesapp.ui.fragment.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.turanbalayev.moviesapp.data.repository.MovieRepository
import com.turanbalayev.moviesapp.model.MovieResponse
import com.turanbalayev.moviesapp.model.MovieResult
import com.turanbalayev.moviesapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExploreViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _data = MutableLiveData<MovieResponse>()
    val data: LiveData<MovieResponse> get() = _data

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    private val _searched_movies = MutableLiveData<List<MovieResult>?>()
    val searched_movies: LiveData<List<MovieResult>?> get() = _searched_movies


    private val _has404 = MutableLiveData<Boolean>(false)
    val has404: LiveData<Boolean> get() = _has404



    init {
        getMovies()
    }




    fun getMovies(){
        viewModelScope.launch {
            _loading.value = true
            when(val result = movieRepository.getMoviesRP()){
                is NetworkResult.Success -> {
                    _data.value = result.data
                    _loading.value = false
                }
                is NetworkResult.Error -> {
                    _error.value = result.message
                    _loading.value = false
                }
            }
        }
    }


    fun searchMoviesByQuery(query: String) {
        val filteredList = mutableListOf<MovieResult>()
        _data.value?.results?.forEach {
            if(it.title.lowercase().contains(query.lowercase())){
                filteredList.add(it)
            }
        }

        if(filteredList.size ==0){
            _has404.value = true
        } else {
            _has404.value = false
            _searched_movies.value = filteredList
        }
    }


    fun clearSearchedMovies(){
        _searched_movies.value = null
    }



}