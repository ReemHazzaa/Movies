package com.reem.movies.app.base.baseUi

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.reem.movies.R
import com.reem.movies.data.remote.networkLayer.NetworkFailure
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    protected var viewDataBinding: ViewDataBinding? = null
    private var loadingProgressBar: LoadingProgressDialog? = null


    @Suppress("UNCHECKED_CAST")
    protected inline fun <T : ViewDataBinding> bind(binding: T?.() -> Unit) {
        binding(viewDataBinding as? T?)
        viewDataBinding?.executePendingBindings()
    }

    private fun showLoading() {
        if (loadingProgressBar == null) {
            loadingProgressBar = LoadingProgressDialog(this)
        }

        if (loadingProgressBar?.isShowing == false) {
            loadingProgressBar?.show()
        }
    }

    private fun hideLoading() {
        loadingProgressBar?.hide()
    }


    protected fun handleLoading(loadingState: Boolean) {
        if (loadingState) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    protected fun showFailure(failure: NetworkFailure) {
        when (failure) {
            is NetworkFailure.NetworkConnection -> showNetworkConnection()
            is NetworkFailure.NetworkError -> showNetworkError()
            is NetworkFailure.ServerError -> showServerError(failure.message)
            is NetworkFailure.UnknownError -> showUnknownError()
        }
    }

    protected fun showNetworkConnection() {
        showToast(getString(R.string.no_connection))
    }

    protected fun showNetworkError() {
        showToast(getString(R.string.no_connection))
    }

    protected fun showServerError(message: String) {
        showToast(message)
    }

    protected fun showUnknownError() {
        showToast(getString(R.string.try_again))
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        loadingProgressBar?.cancel()
        loadingProgressBar = null
        super.onDestroy()
    }

}