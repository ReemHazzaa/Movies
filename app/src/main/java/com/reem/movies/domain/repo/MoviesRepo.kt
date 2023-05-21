package com.reem.movies.domain.repo

import androidx.lifecycle.LiveData
import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.app.ui.home.entity.movie.MovieUiItem
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

    // LOCAL
    // 1. CACHE
    suspend fun getAllCacheMovies(): LiveData<List<MovieUiItem>>
    suspend fun insertCacheItem(item: MovieUiItem)
    suspend fun deleteCacheItem(item: MovieUiItem)

    // 2. FAV
    suspend fun getAllFavMovies(): LiveData<List<FavMovieItem>>
    suspend fun insertFavItem(item: FavMovieItem)
    suspend fun deleteFavItem(item: FavMovieItem)

}