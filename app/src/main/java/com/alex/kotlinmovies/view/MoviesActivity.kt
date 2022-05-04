package com.alex.kotlinmovies.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.alex.kotlinmovies.view.adapter.MovieAdapter
import com.alex.kotlinmovies.databinding.ActivityMoviesBinding
import com.alex.kotlinmovies.viewmodel.MoviesViewModel

class MoviesActivity : AppCompatActivity(), MovieAdapter.ItemClickListener {

    private lateinit var mViewModel: MoviesViewModel
    private lateinit var mRcView: RecyclerView
    private lateinit var mBinding: ActivityMoviesBinding
    private lateinit var mMoviesAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initViews()
        initObservers()
        mViewModel.getMovies()

    }

    private fun initObservers() {
        mViewModel.apply {
            movies.observe(this@MoviesActivity) {
                mMoviesAdapter = MovieAdapter(it, this@MoviesActivity)
                mRcView.adapter = mMoviesAdapter
            }
        }
    }

    private fun initViews() {
        mRcView = mBinding.rvMovies
        mViewModel = MoviesViewModel()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }

}

