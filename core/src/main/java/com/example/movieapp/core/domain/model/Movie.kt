package com.example.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val id: Int,

    val cover: String,

    val title: String,

    val date: String,

    val voteAverage: Double,

    val desc: String,

    val isFav: Boolean
): Parcelable