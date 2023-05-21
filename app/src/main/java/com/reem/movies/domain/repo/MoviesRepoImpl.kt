package com.reem.movies.domain.repo

import androidx.lifecycle.LiveData
import com.reem.movies.app.ui.favorites.entity.FavMovieItem
import com.reem.movies.app.ui.home.entity.movie.MovieItem
import com.reem.movies.data.local.fav.FavDao
import com.reem.movies.data.local.movieCache.MovieCacheDao
import com.reem.movies.data.remote.apiService.MoviesApiService
import com.reem.movies.domain.entity.genreList.GenreListResponse
import com.reem.movies.domain.entity.movieDetails.MovieDetailsResponse
import com.reem.movies.domain.entity.movieList.MovieListResponse
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val moviezApiService: MoviesApiService,
    private val cacheDao: MovieCacheDao,
    private val favDao: FavDao
) :
    MoviesRepo {
    override suspend fun getTopRated(page: Int): MovieListResponse =
        moviezApiService.getTopRated(page)

    override suspend fun getPopular(page: Int): MovieListResponse {
        return moviezApiService.getPopular(page)
    }

    override suspend fun getUpcoming(page: Int): MovieListResponse {
        return moviezApiService.getUpcoming(page)
    }

    override suspend fun getNowPlaying(page: Int): MovieListResponse =
        moviezApiService.getNowPlaying(page)

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse =
        moviezApiService.getMovieDetails(movieId)

    override suspend fun searchMovieByName(
        page: Int,
        includeAdult: Boolean,
        movieName: String
    ): MovieListResponse = moviezApiService.searchMovieByName(page, includeAdult, movieName)

    override suspend fun getGenres(): GenreListResponse {
        return moviezApiService.getGenres()
    }

    override suspend fun getMoviesOfSpecificGenre(genreId: Int): MovieListResponse {
        return moviezApiService.getMoviesOfGenre(genreId)
    }

    override suspend fun getAllCacheMovies(): LiveData<List<MovieItem>> {
        return cacheDao.getAllCachedMovies()
    }

    override suspend fun insertCacheItem(item: MovieItem) {
        cacheDao.insertMovieItem(item)
    }

    override suspend fun deleteCacheItem(item: MovieItem) {
        cacheDao.deleteMovieItem(item)
    }

    override suspend fun getAllFavMovies(): LiveData<List<FavMovieItem>> = favDao.getAllFav()
    override suspend fun insertFavItem(item: FavMovieItem) = favDao.insertFavItem(item)
    override suspend fun deleteFavItem(item: FavMovieItem) = favDao.deleteFavItem(item)

}

