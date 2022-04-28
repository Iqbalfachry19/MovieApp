package com.example.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,

    @SerializedName("poster_path")
    val cover: String,

    val title: String,

    @SerializedName("release_date")
    val date: String,
    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("overview")
    val desc: String
)