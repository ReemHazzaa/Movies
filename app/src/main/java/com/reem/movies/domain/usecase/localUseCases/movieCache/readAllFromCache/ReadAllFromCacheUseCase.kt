package com.reem.movies.domain.usecase.localUseCases.movieCache.readAllFromCache

import androidx.lifecycle.LiveData
import com.reem.movies.app.ui.home.entity.movie.MovieItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class ReadAllFromCacheUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<Any, LiveData<List<MovieItem>>>() {

    override suspend fun execute(params: Any?): LiveData<List<MovieItem>> {
        return moviezRepo.getAllCacheMovies()
    }
}