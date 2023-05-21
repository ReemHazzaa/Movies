package com.reem.movies.domain.usecase.popular

import com.reem.movies.app.ui.home.entity.popular.PopularUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetPopularUseCase.Params, List<PopularUiItem>>() {

    data class Params(
        val page: Int = 1
    )

    override suspend fun execute(params: Params?): List<PopularUiItem> {
        val result = moviezRepo.getPopular(params!!.page)
        return result.results.mapIndexed { index, movie ->
            PopularUiItem(movie.id, movie.title, movie.poster_path, movie.vote_average.toString())
        }
    }
}