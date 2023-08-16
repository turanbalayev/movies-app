package com.turanbalayev.moviesapp.ui.fragment.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turanbalayev.moviesapp.data.repository.MovieRepository
import com.turanbalayev.moviesapp.model.MovieResponse
import com.turanbalayev.moviesapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _data = MutableLiveData<MovieResponse>()
    val data: LiveData<MovieResponse> get() = _data

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


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
}