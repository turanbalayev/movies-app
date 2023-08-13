package com.turanbalayev.moviesapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.turanbalayev.moviesapp.api.MovieApi
import com.turanbalayev.moviesapp.data.HomeRepository
import com.turanbalayev.moviesapp.model.MovieResponse
import com.turanbalayev.moviesapp.model.MovieResult
import com.turanbalayev.moviesapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _data = MutableLiveData<MovieResponse>()
    val data: LiveData<MovieResponse> get() = _data

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error



    fun getMovies(){
        viewModelScope.launch {
            _loading.value = true
            when(val result = homeRepository.getMoviesRP()){
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

    fun isAuth():Boolean{
        return homeRepository.isAuthRP()
    }
}