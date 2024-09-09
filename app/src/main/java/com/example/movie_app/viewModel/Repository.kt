package com.example.movie_app.viewModel


import com.example.movie_app.model.Details
import com.example.movie_app.model.MoviesList
import com.example.movie_app.utils.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMovieList(page: Int): Response<MoviesList> {
        return RetrofitInstance.api.getMovies(page)
    }

    suspend fun getDetailsById(id: Int): Response<Details> {
        return RetrofitInstance.api.getDetailsById(id = id)
    }

}