package com.alex.kotlinmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.databinding.ActivityMovieDetailsBinding
import com.alex.kotlinmovies.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMovieDetailsBinding
    private val mViewModel: MoviesViewModel = MoviesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val id = intent.getIntExtra("id", 0)
        initObservers()
        mViewModel.getMovieDetails(id, APIKEY)

    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@MovieDetailsActivity) {
               setMovieInformation(it)
            }
        }
    }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        mBinding.movieDetailsTitle.text = movieDetails?.title
        mBinding.movieDetailsBodyReleaseDateItem.text = movieDetails?.release_date.toString()
        mBinding.movieDetailsBodyReleaseDateItem.text = movieDetails?.release_date.toString()
        mBinding.movieDetailsBodyScoreItem.text = movieDetails?.vote_average.toString()
        mBinding.movieDetailsBodyMainOverviewItem.text = movieDetails?.overview

        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500" + movieDetails?.backdrop_path)
            .into(mBinding.movieDetailsImage)
    }
}