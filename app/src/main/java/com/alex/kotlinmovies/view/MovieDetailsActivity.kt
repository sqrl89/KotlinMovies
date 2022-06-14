package com.alex.kotlinmovies.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.POSTER_BASE_URL
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.ResultX
import com.alex.kotlinmovies.databinding.ActivityMovieDetailsBinding
import com.alex.kotlinmovies.model.repository.MoviesDBRepositoryImpl
import com.alex.kotlinmovies.view.adapters.TrailerAdapter
import com.alex.kotlinmovies.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMovieDetailsBinding
    private val mViewModel: MoviesViewModel = MoviesViewModel(MoviesDBRepositoryImpl())
    private lateinit var mRcViewTrailer: RecyclerView
    private lateinit var mTrailerAdapter: TrailerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        mViewModel.getMovieDetails(id, APIKEY)
        mViewModel.getTrailer(id, APIKEY)


//        mBinding.shareButton.setOnClickListener {
//            val trailerList : List<ResultX> = listOf()
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
////            intent.putExtra(Intent.EXTRA_TEXT, "Hey Check out this movie:" + resultX?.get(0)?.key)
//            intent.putExtra(Intent.EXTRA_TEXT,
//                "Hey Check out this movie:" + trailerList[0].key)
//            intent.type = "text/plain"
//            startActivity(Intent.createChooser(intent, "Share To:"))
//
//        }
    }

    private fun initViews() {
        mRcViewTrailer = mBinding.rcViewTrailers
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRcViewTrailer.layoutManager = layoutManager

    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@MovieDetailsActivity) {
                setMovieInformation(it)

            }
            trailers.observe(this@MovieDetailsActivity) {
                mTrailerAdapter = TrailerAdapter(it)
                mRcViewTrailer.adapter = mTrailerAdapter
                mTrailerAdapter.setTrailers(it)
            }
        }
    }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        mBinding.movieDetailsTitle.text = movieDetails?.title
        mBinding.movieDetailsBodyReleaseDateItem.text = movieDetails?.release_date?.substring(0, 4)
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
    }

}

