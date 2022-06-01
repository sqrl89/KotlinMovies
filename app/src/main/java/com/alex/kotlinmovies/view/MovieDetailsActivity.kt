package com.alex.kotlinmovies.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alex.kotlinmovies.*
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.ResultX
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

//        openFrag(YouTubePlayerFragment.newInstance(), R.id.frameLayoutYoutube)

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
            trailers.observe(this@MovieDetailsActivity) {
                setTrailer(it)
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

        Picasso.get()
            .load(POSTER_BASE_URL + movieDetails?.backdrop_path)
            .into(mBinding.movieDetailsImage)
    }

    private fun setTrailer(resultX: List<ResultX>) {
        Picasso.get()
            .load("https://img.youtube.com/vi/" + resultX[0].key + "/hqdefault.jpg")
            .into(mBinding.imageTrailer)

        mBinding.trailerName.text = resultX[0].name

        mBinding.cardTrailer.setOnClickListener(View.OnClickListener {
            val appIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("vnd.youtube:" + resultX[0].key)
            )
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(YOUTUBE_BASE_URL + resultX[0].key)
            )
            if (appIntent.resolveActivity(this.packageManager) != null) {
                this.startActivity(appIntent)
            } else {
                this.startActivity(webIntent)
            }
        })
    }

//    private fun openFrag(f: Fragment, idHolder: Int) {
//        supportFragmentManager.beginTransaction()
//            .replace(idHolder, f)
//            .commit()
//    }


}

