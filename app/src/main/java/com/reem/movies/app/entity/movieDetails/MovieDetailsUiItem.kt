package com.reem.movies.app.entity.movieDetails

import com.reem.movies.app.ui.home.entity.genre.GenreUiItem


data class MovieDetailsUiItem(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val voteAvg: String,
    val voteCount: String,
    val genres: List<GenreUiItem>,
)
