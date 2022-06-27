package com.alex.kotlinmovies.view.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.databinding.ItemMovieBinding
import com.alex.kotlinmovies.model.MovieItemModel
import com.squareup.picasso.Picasso

class FavoriteAdapter(private val mItemClickListener: FavItemClickListener) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    interface FavItemClickListener {
        fun onItemClick(movieItemModel: MovieItemModel)
    }

//    private val mItemClickListener: FavItemClickListener? = null
    private var mList = emptyList<MovieItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + mList[position].poster_path)
            .into(holder.binding.itemImage)
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int = mList.size

    fun setList(list: List<MovieItemModel>) {
        mList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItemModel: MovieItemModel) {
            binding.itemImage.setOnClickListener { mItemClickListener.onItemClick(movieItemModel) }
        }
    }
}



