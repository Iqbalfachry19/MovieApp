package com.example.movieapp.core.domain.repository

import com.example.movieapp.core.data.Resource
import com.example.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getListMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}