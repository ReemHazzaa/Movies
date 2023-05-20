package com.reem.movies.app.base.baseUi

import androidx.lifecycle.ViewModel
import com.reem.movies.app.base.liveDataUtils.SingleLiveData
import com.reem.movies.data.remote.networkLayer.NetworkFailure
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel :
    ViewModel() {


    var progressLiveData: SingleLiveData<Boolean>? = SingleLiveData()
        private set

    var failureLiveData: SingleLiveData<NetworkFailure>? = SingleLiveData()
        private set

    var toastRes: SingleLiveData<Int> = SingleLiveData()
        private set

    fun showProgress() {
        progressLiveData?.value = true
    }

    fun hideProgress() {
        progressLiveData?.value = false
    }

    fun showFailure(failure: NetworkFailure) {
        failureLiveData?.value = failure
    }

    fun showResourceToast(res: Int) {
        toastRes.value = res
    }

    fun getExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            hideProgress()
            if (throwable is NetworkFailure)
                showFailure(throwable)
            else showFailure(NetworkFailure.NetworkError)
        }
    }

    class NoneViewModel : BaseViewModel()
}