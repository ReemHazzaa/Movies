package com.reem.movies.app.ui.movieDetails

import androidx.lifecycle.viewModelScope
import com.reem.movies.app.base.baseUi.BaseViewModel
import com.reem.movies.app.base.liveDataUtils.ObserveOnceLiveData
import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.app.entity.movieDetails.MovieDetailsUiItem
import com.reem.movies.domain.usecase.localUseCases.fav.addToFav.AddToFavUseCase
import com.reem.movies.domain.usecase.localUseCases.fav.readAllFromFav.ReadAllFromFavUseCase
import com.reem.movies.domain.usecase.localUseCases.fav.removeItemFromFav.RemoveItemFromFavUseCase
import com.reem.movies.domain.usecase.movieDetails.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val addToFavUseCase: AddToFavUseCase,
    private val readAllFromFavUseCase: ReadAllFromFavUseCase,
    private val removeItemFromFavUseCase: RemoveItemFromFavUseCase
) : BaseViewModel() {
    val detailsLiveData: ObserveOnceLiveData<MovieDetailsUiItem> = ObserveOnceLiveData()

    fun getMovieDetails(id: Int) {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            detailsLiveData.value =
                movieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(movieId = id))
            hideProgress()
        }
    }

    fun addToFav(favMovieItem: FavMovieItem) =
        viewModelScope.launch(Dispatchers.IO + getExceptionHandler()) {
            addToFavUseCase.execute(AddToFavUseCase.Params(favMovieItem))
        }

    fun removeItemFromFav(favMovieItem: FavMovieItem) =
        viewModelScope.launch(Dispatchers.IO + getExceptionHandler()) {
            removeItemFromFavUseCase.execute(RemoveItemFromFavUseCase.Params(favMovieItem))
        }

    suspend fun readAllFav() = readAllFromFavUseCase.execute("")
}