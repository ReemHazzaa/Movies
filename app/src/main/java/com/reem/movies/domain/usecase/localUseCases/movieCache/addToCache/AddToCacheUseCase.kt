package com.reem.movies.domain.usecase.localUseCases.movieCache.addToCache

import com.reem.movies.app.ui.home.entity.movie.MovieItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class AddToCacheUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<AddToCacheUseCase.Params, Any>() {

    data class Params(
        val movieItem: MovieItem
    )

    override suspend fun execute(params: Params?): Any {
        return moviezRepo.insertCacheItem(params!!.movieItem)
    }
}