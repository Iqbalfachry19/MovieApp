package com.example.movieapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.core.ui.MovieAdapter
import com.example.movieapp.presentation.detail.DetailActivity
import com.example.movieapp.favorite.databinding.ActivityFavoriteBinding
import com.example.movieapp.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favBinding: ActivityFavoriteBinding
    private val favViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favBinding.root)

        loadKoinModules(favoriteModule)

        favBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val favAdapter = MovieAdapter()
        favAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }
        }

        favViewModel.favMovie.observe(this) {
            favAdapter.setData(it)
            favBinding.listEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        favBinding.rvMovie.apply {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            adapter = favAdapter
        }
    }
}