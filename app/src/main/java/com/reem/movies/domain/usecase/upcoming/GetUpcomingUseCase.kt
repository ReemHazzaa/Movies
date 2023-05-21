package com.reem.movies.domain.usecase.upcoming

import com.reem.movies.app.ui.home.entity.upcoming.UpcomingUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetUpcomingUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetUpcomingUseCase.Params, List<UpcomingUiItem>>() {

    data class Params(
        val page: Int = 1
    )

    override suspend fun execute(params: Params?): List<UpcomingUiItem> {
        val result = moviezRepo.getUpcoming(params!!.page)
        return result.results.mapIndexed { index, movie ->
            UpcomingUiItem(movie.id, movie.title, movie.poster_path, movie.vote_average.toString())
        }
    }
}