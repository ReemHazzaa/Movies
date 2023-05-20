package com.reem.movies.domain.usecase.localUseCases.movieCache.readAllFromCache

import androidx.lifecycle.LiveData
import com.reem.movies.app.entity.movie.MovieUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class ReadAllFromCacheUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<Any, LiveData<List<MovieUiItem>>>() {

    override suspend fun execute(params: Any?): LiveData<List<MovieUiItem>> {
        return moviezRepo.getAllCacheMovies()
    }
}