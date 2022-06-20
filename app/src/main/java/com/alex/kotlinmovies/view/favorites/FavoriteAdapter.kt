package com.alex.kotlinmovies.view.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.model.MovieItemModel
import com.squareup.picasso.Picasso

class FavoriteAdapter(val mItemClickListener: FavItemClickListener): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    interface FavItemClickListener{
        fun onItemClick(position: Int)
    }

    private var mList = emptyList<MovieItemModel?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + mList[position]?.poster_path)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int = mList.size

    fun setList(list: List<MovieItemModel>) {
        mList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        init{
            ItemView.setOnClickListener { mList[position]?.id?.let{ it -> mItemClickListener.onItemClick(it)} }
        }

    }
}