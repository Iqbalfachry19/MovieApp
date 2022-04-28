package com.example.movieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.core.R
import com.example.movieapp.core.databinding.ItemListMovieBinding
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.utils.ListMovieDiffCallback

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>) {
        val diffCallback = ListMovieDiffCallback(listMovie, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listMovie.clear()
        listMovie.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + movie.cover)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgMovie)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

}