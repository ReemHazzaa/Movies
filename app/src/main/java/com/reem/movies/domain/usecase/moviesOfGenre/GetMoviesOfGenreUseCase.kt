package com.reem.movies.domain.usecase.moviesOfGenre

import com.reem.movies.app.ui.moviesOfGenre.entity.GenreMovieUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetMoviesOfGenreUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetMoviesOfGenreUseCase.Params, List<GenreMovieUiItem>>() {

    data class Params(
        val genreId: Int
    )

    override suspend fun execute(params: Params?): List<GenreMovieUiItem> {
        val result = moviezRepo.getMoviesOfSpecificGenre(params!!.genreId)
        return result.results.mapIndexed { index, movie ->
            GenreMovieUiItem(
                movie.id,
                movie.title,
                movie.poster_path,
                movie.vote_average.toString()
            )
        }
    }
}