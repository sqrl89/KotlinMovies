package com.alex.kotlinmovies.view.detail

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.YOUTUBE_BASE_URL
import com.alex.kotlinmovies.model.ResultX
import com.alex.kotlinmovies.databinding.ItemTrailerBinding
import com.squareup.picasso.Picasso


class TrailerAdapter:
    RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {

    private var trailerList = emptyList<ResultX>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val mBinding =
            ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    fun setTrailers(list: List<ResultX>) {
        trailerList = list
        notifyDataSetChanged()
    }

    inner class TrailerViewHolder(private val mBinding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind() {
            val context = mBinding.cardTrailerItem.context
            mBinding.apply {
                Picasso.get()
                    .load("https://img.youtube.com/vi/" + trailerList[position].key + "/hqdefault.jpg")
                    .into(mBinding.imageTrailer)

                mBinding.trailerName.text = trailerList[position].name
                mBinding.cardTrailerItem.setOnClickListener(View.OnClickListener {
                    val appIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("vnd.youtube:" + trailerList[position].key)
                    )
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(YOUTUBE_BASE_URL + trailerList[position].key)
                    )
                    if (appIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(appIntent)
                    } else {
                        context.startActivity(webIntent)
                    }
                })
            }
        }
    }
}

