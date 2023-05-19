package com.reem.movies.domain.entity.movieList

import com.reem.movies.domain.entity.movie.Movie

data class MovieListResponse(
    val dates: Dates?,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)