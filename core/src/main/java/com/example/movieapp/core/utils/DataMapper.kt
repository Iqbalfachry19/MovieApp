package com.example.movieapp.core.utils

import com.example.movieapp.core.data.source.local.entity.MovieEntity
import com.example.movieapp.core.data.source.remote.response.MovieResponse
import com.example.movieapp.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                cover = it.cover,
                title = it.title,
                date = it.date,
                voteAverage = it.voteAverage,
                desc = it.desc,
                isFav = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                cover = it.cover,
                title = it.title,
                date = it.date,
                voteAverage = it.voteAverage,
                desc = it.desc,
                isFav = it.isFav
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        cover = input.cover,
        title = input.title,
        date = input.date,
        voteAverage = input.voteAverage,
        desc = input.desc,
        isFav = input.isFav
    )
}