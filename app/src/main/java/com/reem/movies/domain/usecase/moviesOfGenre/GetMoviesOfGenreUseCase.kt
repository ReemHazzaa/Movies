package com.reem.movies.domain.usecase.moviesOfGenre

import com.reem.movies.app.ui.home.entity.movie.MovieUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetMoviesOfGenreUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetMoviesOfGenreUseCase.Params, List<MovieUiItem>>() {

    data class Params(
        val genreId: Int
    )

    override suspend fun execute(params: Params?): List<MovieUiItem> {
        val result = moviezRepo.getMoviesOfSpecificGenre(params!!.genreId)
        return result.results.mapIndexed { index, movie ->
            MovieUiItem(movie.id, movie.title, movie.poster_path, movie.vote_average.toString())
        }
    }
}