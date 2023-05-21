package com.reem.movies.app.ui.moviesOfGenre

import androidx.lifecycle.viewModelScope
import com.reem.movies.app.base.baseUi.BaseViewModel
import com.reem.movies.app.base.liveDataUtils.ObserveOnceLiveData
import com.reem.movies.app.ui.moviesOfGenre.entity.GenreMovieUiItem
import com.reem.movies.domain.usecase.moviesOfGenre.GetMoviesOfGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesOfGenreViewModel @Inject constructor(
    private val moviesOfGenreUseCase: GetMoviesOfGenreUseCase
) :
    BaseViewModel() {

    val moviesLiveData = ObserveOnceLiveData<List<GenreMovieUiItem>>()
    val genreName = ObserveOnceLiveData<String>()

    fun getMoviesOfGenre(genreID: Int) {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            moviesLiveData.value =
                moviesOfGenreUseCase.execute(GetMoviesOfGenreUseCase.Params(genreID))
            hideProgress()
        }
    }

}