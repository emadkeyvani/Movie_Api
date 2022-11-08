package com.keyvani.movieapi.server

import com.keyvani.movieapi.model.ResponseMoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {
    @GET("movie")
    fun moviesList(@Query("page") page:Int): Call<ResponseMoviesList>




}