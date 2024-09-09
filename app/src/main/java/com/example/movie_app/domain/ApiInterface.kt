package com.example.movie_app.domain


import com.example.movie_app.model.Details
import com.example.movie_app.model.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("movies?")
    suspend fun getMovies(
        @Query("page")page: Int
    ):Response<MoviesList>
    @GET("movies/{movie_id}")
    suspend fun  getDetailsById(
        @Path("movie_id") id : Int
    ):Response<Details>

}