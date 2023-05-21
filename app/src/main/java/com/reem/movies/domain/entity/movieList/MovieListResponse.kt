package com.reem.movies.domain.entity.movieList

import com.reem.movies.domain.entity.movie.Movie

data class MovieListResponse(
    val dates: Dates? = null,
    val page: Int = 0,
    val results: List<Movie>,
    val total_pages: Int = 0,
    val total_results: Int = 0
)