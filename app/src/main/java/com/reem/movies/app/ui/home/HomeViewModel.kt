package com.reem.movies.app.ui.home

import androidx.lifecycle.viewModelScope
import com.reem.movies.app.base.baseUi.BaseViewModel
import com.reem.movies.app.base.liveDataUtils.ObserveOnceLiveData
import com.reem.movies.app.entity.genre.GenreUiItem
import com.reem.movies.app.entity.movie.MovieUiItem
import com.reem.movies.domain.usecase.genres.GetGenresUseCase
import com.reem.movies.domain.usecase.nowPlaying.GetNowPlayingUseCase
import com.reem.movies.domain.usecase.popular.GetPopularUseCase
import com.reem.movies.domain.usecase.upcoming.GetUpcomingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val upcomingUseCase: GetUpcomingUseCase,
    private val nowPlayingUseCase: GetNowPlayingUseCase
) :
    BaseViewModel() {

    val popularLiveData = ObserveOnceLiveData<List<MovieUiItem>>()
    val genresLiveData = ObserveOnceLiveData<List<GenreUiItem>>()
    val upcomingLiveData = ObserveOnceLiveData<List<MovieUiItem>>()
    val nowPlayingLiveData = ObserveOnceLiveData<List<MovieUiItem>>()

    fun getPopular(page: Int) {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            popularLiveData.value = popularUseCase.execute(GetPopularUseCase.Params(page))
            hideProgress()
        }
    }

    fun getGenres() {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            genresLiveData.value = genresUseCase.execute(null)
            hideProgress()
        }
    }

    fun getUpcoming(page: Int) {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            upcomingLiveData.value = upcomingUseCase.execute(GetUpcomingUseCase.Params(page))
            hideProgress()
        }
    }

    fun getNowPlaying(page: Int) {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            nowPlayingLiveData.value = nowPlayingUseCase.execute(GetNowPlayingUseCase.Params(page))
            hideProgress()
        }
    }

}