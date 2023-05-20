package com.reem.movies.domain.usecase.localUseCases.fav.addToFav

import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class AddToFavUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<AddToFavUseCase.Params, Any>() {

    data class Params(
        val favMovieItem: FavMovieItem
    )

    override suspend fun execute(params: Params?): Any {
        return moviezRepo.insertFavItem(params!!.favMovieItem)
    }
}