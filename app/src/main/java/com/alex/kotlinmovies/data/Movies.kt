package com.alex.kotlinmovies.data

data class Movies(
    var page: Int,
    var results: List<Result>,
    var total_pages: Int,
    var total_results: Int
    )
