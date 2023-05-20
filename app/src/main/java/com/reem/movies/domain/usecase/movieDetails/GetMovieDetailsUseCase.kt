package com.reem.movies.domain.usecase.movieDetails

import com.reem.movies.app.entity.genre.GenreUiItem
import com.reem.movies.app.entity.movieDetails.MovieDetailsUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetMovieDetailsUseCase.Params, MovieDetailsUiItem>() {

    data class Params(
        val movieId: Int
    )

    override suspend fun execute(params: Params?): MovieDetailsUiItem {
        val result = moviezRepo.getMovieDetails(params!!.movieId)
        return MovieDetailsUiItem(
            id = result.id,
            title = result.title,
            overview = result.overview,
            imageUrl = result.poster_path,
            voteAvg = result.vote_average?.toString() ?: "",
            voteCount = result.vote_count?.toString() ?: "",
            genres = result.genres.map { item ->
                GenreUiItem(item.name)
            }
        )
    }
}