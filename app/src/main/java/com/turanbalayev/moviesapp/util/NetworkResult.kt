package com.turanbalayev.moviesapp.util

sealed class NetworkResult<out T: Any> {

    class Success<out T: Any>(val data: T): NetworkResult<T>()
    class Error(val message: String): NetworkResult<Nothing>()

}