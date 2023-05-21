package com.reem.movies.domain.usecase.nowPlaying

import com.reem.movies.app.ui.home.entity.nowPlaying.NowPlayingUiItem
import com.reem.movies.domain.repo.MoviesRepo
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val moviezRepo: MoviesRepo) :
    BaseUseCase<GetNowPlayingUseCase.Params, List<NowPlayingUiItem>>() {

    data class Params(
        val page: Int = 1
    )

    override suspend fun execute(params: Params?): List<NowPlayingUiItem> {
        val result = moviezRepo.getNowPlaying(params!!.page)
        return result.results.mapIndexed { index, movie ->
            NowPlayingUiItem(
                movie.id,
                movie.title,
                movie.poster_path,
                movie.vote_average.toString()
            )
        }
    }
}