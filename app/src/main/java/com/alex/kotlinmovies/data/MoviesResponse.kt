package com.alex.kotlinmovies.data

data class Movies(
    var page: Int,
    var results: List<Result>,
    var total_pages: Int,
    var total_results: Int
    )

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: String,
    val vote_count: Int
)

data class User(val email: String, val uid: String)

data class Trailer(
    val id: Int,
    val results: List<ResultX>
)

data class ResultX(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val published_at: String,
    val site: String,
    val size: Int,
    val type: String
)

data class MovieDetails(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: Any,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val results: List<ResultX>
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class Genre(
    val id: Int,
    val name: String
)


