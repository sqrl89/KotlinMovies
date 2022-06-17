package com.alex.kotlinmovies

import com.alex.kotlinmovies.data.room.repository.MoviesRepositoryRealization
import com.alex.kotlinmovies.view.MoviesActivity

const val APIKEY = "c0b0dee8d463adb2d456242c92fd1696"
const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="
const val API_BASE_URL = "https://api.themoviedb.org/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"
const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20
const val YOUTUBE_API_KEY: String = "AIzaSyAGYLzqvpEt3RECmwamycY217516f6frug"
lateinit var MOVIES: MoviesActivity
lateinit var REALIZATION: MoviesRepositoryRealization
