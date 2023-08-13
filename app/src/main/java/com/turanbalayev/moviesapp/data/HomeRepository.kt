package com.turanbalayev.moviesapp.data

import com.google.firebase.auth.FirebaseAuth
import com.turanbalayev.moviesapp.api.MovieApi
import com.turanbalayev.moviesapp.model.MovieResponse
import com.turanbalayev.moviesapp.model.MovieResult
import com.turanbalayev.moviesapp.util.NetworkResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: MovieApi,
    private val auth: FirebaseAuth
) {


    suspend fun getMoviesRP(): NetworkResult<MovieResponse> {

        return try {
            val response = api.getTopRatedMovies()

            if (response.isSuccessful && response.body() == null) {
                NetworkResult.Success(MovieResponse(0, emptyList<MovieResult>(),0,0))
            } else if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else if (response.errorBody() != null) {
                NetworkResult.Error(message = response.errorBody().toString())
            } else {
                NetworkResult.Error(message = "Something went wrong")
            }

        } catch (e: HttpException) {
            NetworkResult.Error(message = e.localizedMessage?.toString() ?: "Something went wrong")
        } catch (e: IOException) {
            NetworkResult.Error(message = "Please check your network connection")
        } catch (e: Exception) {
            NetworkResult.Error(message = e.localizedMessage?.toString() ?: "Something went wrong")
        }


    }

    fun isAuthRP(): Boolean {
        return auth.currentUser != null
    }


}