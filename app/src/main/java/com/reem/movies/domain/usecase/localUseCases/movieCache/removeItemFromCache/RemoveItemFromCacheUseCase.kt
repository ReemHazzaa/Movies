package com.reem.movies.domain.usecase.localUseCases.movieCache.removeItemFromCache

import com.reem.movies.app.ui.home.entity.movie.MovieItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class RemoveItemFromCacheUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<RemoveItemFromCacheUseCase.Params, Any>() {

    data class Params(
        val movieItem: MovieItem
    )

    override suspend fun execute(params: Params?): Any {
        return moviezRepo.deleteCacheItem(params!!.movieItem)
    }
}