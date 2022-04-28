package com.example.movieapp.core.domain.useCase

import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository


class MovieInteractor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getListMovie() = movieRepository.getListMovie()

    override fun getFavMovie() = movieRepository.getFavoriteMovie()

    override fun setFavMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}