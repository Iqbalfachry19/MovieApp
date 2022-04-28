package com.example.movieapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.movieapp.core.domain.model.Movie

import com.example.movieapp.core.domain.useCase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setMovieFav(movie: Movie, state: Boolean) = movieUseCase.setFavMovie(movie, state)
}