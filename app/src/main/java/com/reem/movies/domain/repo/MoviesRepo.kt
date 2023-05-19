package com.reem.movies.domain.repo

import com.reem.movies.domain.entity.genreList.GenreListResponse
import com.reem.movies.domain.entity.movieDetails.MovieDetailsResponse
import com.reem.movies.domain.entity.movieList.MovieListResponse

interface MoviesRepo {
    suspend fun getTopRated(page: Int): MovieListResponse

    suspend fun getPopular(page: Int): MovieListResponse

    suspend fun getUpcoming(page: Int): MovieListResponse

    suspend fun getNowPlaying(page: Int): MovieListResponse

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse

    suspend fun searchMovieByName(
        page: Int,
        includeAdult: Boolean,
        movieName: String
    ): MovieListResponse

    suspend fun getGenres(): GenreListResponse

    suspend fun getMoviesOfSpecificGenre(genreId: Int): MovieListResponse

}