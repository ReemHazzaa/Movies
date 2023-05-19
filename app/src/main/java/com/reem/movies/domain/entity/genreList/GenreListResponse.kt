package com.reem.movies.domain.entity.genreList

import com.reem.movies.domain.entity.genre.Genre

data class GenreListResponse(
    val genres: List<Genre>,
)