package com.alex.kotlinmovies.view.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.*
import com.alex.kotlinmovies.databinding.ActivityMovieDetailsBinding
import com.alex.kotlinmovies.model.MovieDetails
import com.alex.kotlinmovies.model.MovieItemModel
import com.alex.kotlinmovies.model.repository.SharedPref
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private val mViewModel by viewModels<MovieDetailViewModel>()
    private lateinit var mBinding: ActivityMovieDetailsBinding
    private lateinit var mRcViewTrailer: RecyclerView
    private val mTrailerAdapter by lazy { TrailerAdapter() }
    private var isFavorite: Boolean = false
    private lateinit var currentMovie: MovieItemModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()

    }

    private fun init() {
        val id = intent.getIntExtra("id", 0)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRcViewTrailer = mBinding.rcViewTrailers
        mRcViewTrailer.adapter = mTrailerAdapter
        mRcViewTrailer.layoutManager = layoutManager
        mViewModel.movieDetails.observe(this) {
            setMovieInformation(it.body()!!)
        }
        mViewModel.getMovieDetailsRetrofit(id, APIKEY)
        mViewModel.trailers.observe(this) {
            mTrailerAdapter.setTrailers(it.body()!!.results)
        }
        mViewModel.getTrailersRetrofit(id, APIKEY)

    }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        mBinding.movieDetailsTitle.text = movieDetails?.title
        mBinding.movieDetailsBodyReleaseDateItem.text =
            movieDetails?.release_date?.substring(0, 4)
        mBinding.movieDetailsBodyScoreItem.text = movieDetails?.vote_average.toString()
        mBinding.movieDetailsBodyMainOverviewItem.text = movieDetails?.overview
        mBinding.movieDetailsTagline.text = movieDetails?.tagline
        if (movieDetails?.tagline == "") mBinding.movieDetailsTagline.visibility = View.GONE
        mBinding.movieDetailsBodyCountry.text = movieDetails?.production_countries?.get(0)?.name
        mBinding.movieDetailsBodyGenre.text = movieDetails?.genres?.get(0)?.name?.capitalize()
        mBinding.movieDetailsBodyGenre2.text = movieDetails?.genres?.get(1)?.name?.capitalize()
        Picasso.get()
            .load(POSTER_BASE_URL + movieDetails?.backdrop_path)
            .into(mBinding.movieDetailsImage)


        val valueBoolean = SharedPref.getFavorite(MOVIES, movieDetails?.id.toString())
        if (isFavorite != valueBoolean) {
            mBinding.favoriteButton.setImageResource(R.drawable.ic_favorite_white_24dp)
        } else {
            mBinding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
        }

//        val gotMovie = intent.extras?.getSerializable("id")
//        currentMovie = gotMovie as MovieItemModel
            mBinding.favoriteButton.setOnClickListener {
                if (isFavorite == valueBoolean) {
                    mBinding.favoriteButton.setImageResource(R.drawable.ic_favorite_white_24dp)
                    SharedPref.setFavorite(MOVIES, movieDetails?.id.toString(), true)
                    mViewModel.insert(currentMovie) {}
                    isFavorite = true
                } else {
                    mBinding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
                    SharedPref.setFavorite(MOVIES, movieDetails?.id.toString(), false)
                    mViewModel.delete(currentMovie) {}
                    isFavorite = false
                }
            }


        mBinding.shareButton.setOnClickListener {
            val trailerList = mViewModel.trailers.value?.body()?.results
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,
                "Посмотри фильм: $YOUTUBE_BASE_URL${trailerList?.get(0)?.key}")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Поделиться:"))
        }
    }
}





