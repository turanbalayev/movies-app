package com.turanbalayev.moviesapp.ui.fragment.topmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turanbalayev.moviesapp.api.MovieApi
import com.turanbalayev.moviesapp.model.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class TopMoviesViewModel @Inject constructor(private val api: MovieApi) : ViewModel() {

    private val _data = MutableLiveData<MovieResponse>()
    val data: LiveData<MovieResponse> get() = _data

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun getMovies(){
        viewModelScope.launch {
            _loading.value = true
            try {
                _loading.value = true
                val response = api.getTopRatedMovies()
                if(response.isSuccessful){
                    if (response.body() == null){
                        _error.value = "Response body is null"
                        _loading.value = false
                    }

                    response.body().let {
                        _data.value = it
                        _loading.value = false
                    }

                } else{
                    _error.value = response.errorBody().toString()
                    _loading.value = false
                }

            } catch (e: Exception){
                _error.value = e.localizedMessage?.toString() ?: "Unexpected Error"
                _loading.value = false
            }
        }
    }
}