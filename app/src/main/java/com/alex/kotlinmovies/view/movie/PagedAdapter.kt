package com.alex.kotlinmovies.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.databinding.ItemMovieBinding
import com.alex.kotlinmovies.model.MovieItemModel
import com.alex.kotlinmovies.view.movie.PagedAdapter.ImageViewHolder
import com.squareup.picasso.Picasso

class PagedAdapter(private val mItemClickListener: PagedItemClickListener) :
    PagingDataAdapter<MovieItemModel, ImageViewHolder>(diffCallback) {

    interface PagedItemClickListener {
        fun onItemClick(movieItemModel: MovieItemModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${getItem(position)?.poster_path}")
            .into(holder.binding.itemImage)
    }

    inner class ImageViewHolder(
        val binding: ItemMovieBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.itemImage.setOnClickListener {
                val position = bindingAdapterPosition
                val item = getItem(position)
                item?.let { it -> mItemClickListener.onItemClick(it) }
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieItemModel>() {
            override fun areItemsTheSame(
                oldItem: MovieItemModel,
                newItem: MovieItemModel,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieItemModel,
                newItem: MovieItemModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
