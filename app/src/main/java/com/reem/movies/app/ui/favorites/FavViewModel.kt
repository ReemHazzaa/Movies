package com.reem.movies.app.ui.favorites

import com.reem.movies.app.base.baseUi.BaseViewModel
import com.reem.movies.domain.usecase.localUseCases.fav.readAllFromFav.ReadAllFromFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val readAllFromFavUseCase: ReadAllFromFavUseCase
) : BaseViewModel() {
    suspend fun readAllFav() = readAllFromFavUseCase.execute("")
}