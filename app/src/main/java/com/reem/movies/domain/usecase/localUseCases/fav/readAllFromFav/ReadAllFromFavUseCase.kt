package com.reem.movies.domain.usecase.localUseCases.fav.readAllFromFav

import androidx.lifecycle.LiveData
import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class ReadAllFromFavUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<Any, LiveData<List<FavMovieItem>>>() {

    override suspend fun execute(params: Any?): LiveData<List<FavMovieItem>> {
        return moviezRepo.getAllFavMovies()
    }
}