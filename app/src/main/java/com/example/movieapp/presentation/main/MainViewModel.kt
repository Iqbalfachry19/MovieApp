package com.example.movieapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movieapp.core.domain.useCase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getListMovie().asLiveData()
}