package com.example.movieapp.di

import com.example.movieapp.core.domain.useCase.MovieInteractor
import com.example.movieapp.core.domain.useCase.MovieUseCase
import com.example.movieapp.presentation.detail.DetailViewModel
import com.example.movieapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
