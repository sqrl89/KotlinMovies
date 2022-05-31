package com.alex.kotlinmovies.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.capitalize
import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.POSTER_BASE_URL
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.databinding.ActivityMovieDetailsBinding
import com.alex.kotlinmovies.model.repository.MoviesDBRepositoryImpl
import com.alex.kotlinmovies.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMovieDetailsBinding
    private val mViewModel: MoviesViewModel = MoviesViewModel(MoviesDBRepositoryImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val id = intent.getIntExtra("id", 0)
        initObservers()
        mViewModel.getMovieDetails(id, APIKEY)
        mViewModel.getTrailer(id, APIKEY)

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
        mBinding.movieDetailsBodyReleaseDateItem.text = movieDetails?.release_date?.substring(0, 4)
        mBinding.movieDetailsBodyScoreItem.text = movieDetails?.vote_average.toString()
        mBinding.movieDetailsBodyMainOverviewItem.text = movieDetails?.overview
        mBinding.movieDetailsTagline.text = movieDetails?.tagline
        if(movieDetails?.tagline == "") mBinding.movieDetailsTagline.visibility = View.GONE
        mBinding.movieDetailsBodyCountry.text = movieDetails?.production_countries?.get(0)?.name
        mBinding.movieDetailsBodyGenre.text = movieDetails?.genres?.get(0)?.name?.capitalize()
        mBinding.movieDetailsBodyMainTrailerURL.text = movieDetails?.url?.get(0)?.key.toString()
        Log.d("test", movieDetails?.url.toString())

        Picasso.get()
            .load(POSTER_BASE_URL + movieDetails?.backdrop_path)
            .into(mBinding.movieDetailsImage)
    }
}