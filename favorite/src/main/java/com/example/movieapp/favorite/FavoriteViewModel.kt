package com.example.movieapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movieapp.core.domain.useCase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favMovie = movieUseCase.getFavMovie().asLiveData()
}