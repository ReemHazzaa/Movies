package com.reem.movies.domain.usecase.genres

import com.reem.movies.app.ui.home.entity.genre.GenreUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<Any, List<GenreUiItem>>() {

    override suspend fun execute(params: Any?): List<GenreUiItem> {
        val result = moviezRepo.getGenres()
        return result.genres.mapIndexed { index, genre ->
            GenreUiItem(genre.name)
        }
    }
}