package com.example.movieapp.core.data.source.local.room

import androidx.room.*
import com.example.movieapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getListMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFav = 1")
    fun getFavMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun setFavMovie(movie: MovieEntity)
}