package com.example.movieapp.core.data.source.local

import com.example.movieapp.core.data.source.local.entity.MovieEntity
import com.example.movieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {


    fun getListMovie(): Flow<List<MovieEntity>> = movieDao.getListMovie()

    fun getFavMovie(): Flow<List<MovieEntity>> = movieDao.getFavMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavMovie(movie: MovieEntity, state: Boolean) {
        movie.isFav = state
        movieDao.setFavMovie(movie)
    }
}