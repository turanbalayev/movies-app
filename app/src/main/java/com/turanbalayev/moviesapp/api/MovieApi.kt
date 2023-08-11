package com.turanbalayev.moviesapp.api

import com.turanbalayev.moviesapp.model.MovieResponse
import com.turanbalayev.moviesapp.model.MovieResult
import com.turanbalayev.moviesapp.util.Constant.API_KEY
import com.turanbalayev.moviesapp.util.Constant.API_READ_ACCESS_TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Header("accept") accept: String = "accept: application/json",
        @Header("Authorization") authorization: String = "Bearer $API_READ_ACCESS_TOKEN",
        @Query("language") language: String = "en-US",
        @Query("page") pageNumber: Int = 1
    ): Response<MovieResponse>





}