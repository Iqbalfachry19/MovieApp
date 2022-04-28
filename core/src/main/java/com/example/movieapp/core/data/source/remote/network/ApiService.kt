package com.example.movieapp.core.data.source.remote.network



import com.example.movieapp.core.data.source.remote.response.ListMovieResponse
import com.example.movieapp.core.utils.Constant.API_KEY
import retrofit2.http.GET

interface ApiService {


    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getMovies(): ListMovieResponse

}