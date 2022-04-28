package com.example.movieapp.core.domain.useCase


import com.example.movieapp.core.data.Resource
import com.example.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListMovie(): Flow<Resource<List<Movie>>>

    fun getFavMovie(): Flow<List<Movie>>

    fun setFavMovie(movie: Movie, state: Boolean)
}