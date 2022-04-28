package com.example.movieapp.presentation.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.core.data.Resource
import com.example.movieapp.core.ui.MovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.presentation.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { movie ->
            Intent(this, DetailActivity::class.java).also {
                it.putExtra(DetailActivity.DATA, movie)
                startActivity(it)
            }
        }

        movieViewModel.movie.observe(this) {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> true.progressbar()
                    is Resource.Success -> {
                        false.progressbar()
                        it.data?.let { it1 -> movieAdapter.setData(it1) }
                    }
                    is Resource.Error -> {
                        false.progressbar()
                        binding.viewError.apply {
                            root.visibility = View.INVISIBLE
                            tvError.text = it.message
                        }

                    }
                }
            }
        }

        setRecyclerView()

        val uri = Uri.parse("movie://favorite")
        binding.fab.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun setRecyclerView() {
        with(binding.rvMovie) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun Boolean.progressbar() {
        binding.progressBar.visibility = if (this) View.VISIBLE else View.INVISIBLE
    }
}