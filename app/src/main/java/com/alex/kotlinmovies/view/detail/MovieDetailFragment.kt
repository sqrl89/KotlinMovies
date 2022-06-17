package com.alex.kotlinmovies.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.POSTER_BASE_URL
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.MovieItemModel
import com.alex.kotlinmovies.databinding.FragmentDetailBinding
import com.alex.kotlinmovies.model.repository.SharedPref
import com.alex.kotlinmovies.view.adapters.TrailerAdapter
import com.alex.kotlinmovies.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private var mBinding: FragmentDetailBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mRcViewTrailer: RecyclerView
    private lateinit var mTrailerAdapter: TrailerAdapter
    private lateinit var currentMovie: MovieItemModel
//    private lateinit var movieDetails: MovieDetails
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("id") as MovieItemModel
//        movieDetails = arguments?.getSerializable("id") as MovieDetails
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val valueBoolean = SharedPref.getFavorite(MOVIES, currentMovie.id.toString())
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(isFavorite != valueBoolean){
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_white_24dp)
        }
        else{
            binding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
        }

//приравнять id
        binding.movieDetailsTitle.text = currentMovie.title
        binding.movieDetailsBodyReleaseDateItem.text = currentMovie.release_date.substring(0, 4)
        binding.movieDetailsBodyMainOverviewItem.text = currentMovie.overview
        binding.movieDetailsBodyScoreItem.text = currentMovie.vote_average.toString()

//        binding.movieDetailsTagline.text = movieDetails.tagline
//        if (movieDetails.tagline == "") binding.movieDetailsTagline.visibility = View.GONE
//        binding.movieDetailsBodyCountry.text = movieDetails.production_countries[0].name
//        binding.movieDetailsBodyGenre.text = movieDetails.genres[0].name.capitalize()
//        binding.movieDetailsBodyGenre2.text = movieDetails.genres[1].name.capitalize()
        Picasso.get()
            .load(POSTER_BASE_URL + currentMovie.backdrop_path)
            .into(binding.movieDetailsImage)

        mRcViewTrailer = binding.rcViewTrailers

        binding.favoriteButton.setOnClickListener {
            if (isFavorite == valueBoolean) {
                binding.favoriteButton.setImageResource(R.drawable.ic_favorite_white_24dp)
                SharedPref.setFavorite(MOVIES, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie) {}
                isFavorite = true
            } else {
                binding.favoriteButton.setImageResource(R.drawable.ic_favorite_border_white_24dp)
                SharedPref.setFavorite(MOVIES, currentMovie.id.toString(), false)
                viewModel.delete(currentMovie) {}
                isFavorite = false
            }
        }
    }


//    private fun setMovieInformation(movieDetails: MovieDetails?) {
//        binding.movieDetailsTitle.text = movieDetails?.title
//        binding.movieDetailsBodyReleaseDateItem.text =
//            movieDetails?.release_date?.substring(0, 4)
//        binding.movieDetailsBodyScoreItem.text = movieDetails?.vote_average.toString()
//        binding.movieDetailsBodyMainOverviewItem.text = movieDetails?.overview
//        binding.movieDetailsTagline.text = movieDetails?.tagline
//        if (movieDetails?.tagline == "") binding.movieDetailsTagline.visibility = View.GONE
//        binding.movieDetailsBodyCountry.text = movieDetails?.production_countries?.get(0)?.name
//        binding.movieDetailsBodyGenre.text = movieDetails?.genres?.get(0)?.name?.capitalize()
//        binding.movieDetailsBodyGenre2.text = movieDetails?.genres?.get(1)?.name?.capitalize()
//    }


//private fun initViews() {
//        mRcViewTrailer = mBinding.rcViewTrailers
//        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        mRcViewTrailer.layoutManager = layoutManager
//
//    }
//
//    private fun initObservers() {
//        mViewModel.apply {
//            movieDetails.observe(this@MovieDetailsActivity) {
//                setMovieInformation(it)
//
//            }
//            trailers.observe(this@MovieDetailsActivity) {
//                mTrailerAdapter = TrailerAdapter(it)
//                mRcViewTrailer.adapter = mTrailerAdapter
//                mTrailerAdapter.setTrailers(it)
//            }
//        }
//    }
//


}

