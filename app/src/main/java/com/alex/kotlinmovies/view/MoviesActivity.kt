package com.alex.kotlinmovies.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.alex.kotlinmovies.MOVIES
import com.alex.kotlinmovies.R
import com.alex.kotlinmovies.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private var mBinding: ActivityMoviesBinding? = null
    private val binding get() = mBinding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MOVIES = this

        navController = Navigation.findNavController(this, R.id.nav_host)

        binding.bottomNavigation.setupWithNavController(navController)

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}

