package com.reem.movies.domain.usecase.localUseCases.movieCache.addToCache

import com.reem.movies.app.entity.movie.MovieUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class AddToCacheUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<AddToCacheUseCase.Params, Any>() {

    data class Params(
        val movieUiItem: MovieUiItem
    )

    override suspend fun execute(params: Params): Any {
        return moviezRepo.insertCacheItem(params.movieUiItem)
    }
}