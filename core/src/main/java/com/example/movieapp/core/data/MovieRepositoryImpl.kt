package com.example.movieapp.core.data

import com.example.movieapp.core.data.source.local.LocalDataSource
import com.example.movieapp.core.data.source.remote.RemoteDataSource
import com.example.movieapp.core.data.source.remote.network.ApiResponse
import com.example.movieapp.core.data.source.remote.response.MovieResponse
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import com.example.movieapp.core.utils.AppExecutors
import com.example.movieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepository {


    override fun getListMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getListMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getListMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavMovie(moviesEntity, state)
        }
    }


}