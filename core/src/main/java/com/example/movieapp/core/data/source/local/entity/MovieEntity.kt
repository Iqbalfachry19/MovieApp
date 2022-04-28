package com.example.movieapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    val id: Int,

    val cover: String,

    val title: String,

    val date: String,

    val voteAverage: Double,

    val desc: String,

    var isFav: Boolean = false
)