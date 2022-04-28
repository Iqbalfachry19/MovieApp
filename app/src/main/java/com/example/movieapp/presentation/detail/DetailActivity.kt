package com.example.movieapp.presentation.detail

import android.graphics.Color

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movieapp.R
import com.example.movieapp.core.domain.model.Movie

import com.example.movieapp.core.utils.dateConverter
import com.example.movieapp.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        detailBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val detailMovie = intent.getParcelableExtra<Movie>(DATA)
        showDetailMovie(detailMovie)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            with(detailBinding) {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + detailMovie.cover)
                    .transform(RoundedCorners(20))
                    .into(imgDetail)

                tvTitleDetail.text = detailMovie.title
                tvDateDetail.text =  StringBuilder().append(dateConverter(detailMovie.date)).append(" Released")
                tvRatingDetail.text = detailMovie.voteAverage.toString()
                if (detailMovie.voteAverage < 5) {
                    tvRatingDetail.setTextColor(Color.RED)
                }
                tvDescDetail.text = detailMovie.desc

                var favStatus = detailMovie.isFav
                setStatusFav(favStatus)
                fab.setOnClickListener {
                    favStatus = !favStatus
                    detailViewModel.setMovieFav(detailMovie, favStatus)
                    setStatusFav(favStatus)
                }
            }
        }
    }

    private fun setStatusFav(statusFav: Boolean) {
        if (statusFav) {
            detailBinding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fav_true
                )
            )
        } else {
            detailBinding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_fav_false
                )
            )
        }
    }

    companion object {
        const val DATA = "data"
    }

}