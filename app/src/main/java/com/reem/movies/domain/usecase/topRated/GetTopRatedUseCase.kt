package com.reem.movies.domain.usecase.topRated

import com.reem.movies.app.ui.topRated.entity.TopRatedUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetTopRatedUseCase.Params, List<TopRatedUiItem>>() {

    data class Params(
        val page: Int = 1
    )

    override suspend fun execute(params: Params?): List<TopRatedUiItem> {
        val result = moviezRepo.getTopRated(params!!.page)
        return result.results.mapIndexed { index, movie ->
            TopRatedUiItem(movie.id, movie.title, movie.poster_path, movie.vote_average.toString())
        }
    }
}