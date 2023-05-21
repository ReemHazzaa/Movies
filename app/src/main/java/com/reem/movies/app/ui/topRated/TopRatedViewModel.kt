package com.reem.movies.app.ui.topRated

import androidx.lifecycle.viewModelScope
import com.reem.movies.app.base.baseUi.BaseViewModel
import com.reem.movies.app.base.liveDataUtils.ObserveOnceLiveData
import com.reem.movies.app.ui.topRated.entity.TopRatedUiItem
import com.reem.movies.domain.usecase.topRated.GetTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val topRatedUseCase: GetTopRatedUseCase
) :
    BaseViewModel() {

    val topRatedLiveData = ObserveOnceLiveData<List<TopRatedUiItem>>()

    fun getTopRated(page: Int) {
        viewModelScope.launch(getExceptionHandler()) {
            showProgress()
            topRatedLiveData.value = topRatedUseCase.execute(GetTopRatedUseCase.Params(page))
            hideProgress()
        }
    }

}