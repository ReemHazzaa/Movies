package com.reem.movies.domain.usecase.localUseCases.fav.removeItemFromFav

import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class RemoveItemFromFavUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<RemoveItemFromFavUseCase.Params, Any>() {

    data class Params(
        val favMovieItem: FavMovieItem
    )

    override suspend fun execute(params: Params): Any {
        return moviezRepo.deleteFavItem(params.favMovieItem)
    }
}