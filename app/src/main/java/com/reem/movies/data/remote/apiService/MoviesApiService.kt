package com.reem.movies.data.remote.apiService

import com.reem.movies.domain.entity.genreList.GenreListResponse
import com.reem.movies.domain.entity.movieDetails.MovieDetailsResponse
import com.reem.movies.domain.entity.movieList.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    // POV
    @GET("search/movie")
    suspend fun searchMovieByName(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("query") query: String
    ): MovieListResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int
    ): MovieListResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreListResponse

    @GET("discover/movie")
    suspend fun getMoviesOfGenre(
        @Query("with_genres") withGenres: Int
    ): MovieListResponse
}