package com.reem.movies.domain.usecase.searchByName

import com.reem.movies.app.entity.search.SearchUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class SearchByNameUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<SearchByNameUseCase.Params, List<SearchUiItem>>() {

    data class Params(
        val page: Int,
        val includeAdult: Boolean,
        val movieName: String
    )

    override suspend fun execute(params: Params?): List<SearchUiItem> {
        val result =
            moviezRepo.searchMovieByName(params!!.page, params.includeAdult, params.movieName)
        return result.results.map { item ->
            SearchUiItem(item.id, item.title, item.poster_path, item.vote_average.toString())
        }
    }
}