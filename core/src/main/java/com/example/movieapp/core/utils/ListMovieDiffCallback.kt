package com.example.movieapp.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.core.domain.model.Movie

class ListMovieDiffCallback(private val mOldList: ArrayList<Movie>, private val mNewList: List<Movie>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldList.size
    }
    override fun getNewListSize(): Int {
        return mNewList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].id == mNewList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            mOldList[oldItemPosition].id != mNewList[newItemPosition].id->{
                false
            }
            else->true
        }
    }
}