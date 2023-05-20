package com.reem.movies.app.base.baseUi

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.reem.movies.R
import com.reem.movies.data.remote.networkLayer.NetworkFailure


abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    protected abstract val layoutResId: Int
    protected open val dataBindingEnabled: Boolean = true
    protected lateinit var viewDataBinding: VB
    private var loadingProgressBar: LoadingProgressDialog? = null

    // var recyclerAdapter: GeneralListAdapter? = null
    abstract val mViewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View?
        if (dataBindingEnabled) {
            viewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
            view = viewDataBinding.root
        } else {
            view = inflater.inflate(layoutResId, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleGeneralObservers()
        bind()
    }

    private fun handleGeneralObservers() {
        mViewModel.progressLiveData?.observe(viewLifecycleOwner) {
            it?.let { handleLoading(it) }
        }
        mViewModel.failureLiveData?.observe(viewLifecycleOwner) {
            it?.let { showFailure(it) }
        }
        mViewModel.toastRes.observe(viewLifecycleOwner) {
            it?.let {
                showToast(getString(it))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    protected fun bind() {
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    protected fun handleLoading(loadingState: Boolean) {
        if (loadingState) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    protected fun showLoading() {
        if (loadingProgressBar == null) {
            loadingProgressBar = LoadingProgressDialog(context)
        }

        if (loadingProgressBar?.isShowing == false) {
            loadingProgressBar?.show()
        }
    }

    protected fun hideLoading() {
        loadingProgressBar?.cancel()
        loadingProgressBar?.hide()
    }

    protected fun showFailure(failure: NetworkFailure) {
        Log.d("showFailure: ", failure.message ?: "")
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
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showGeneralDialog(
        title: String?,
        description: String?,
        positiveLabel: String?,
        negativeLabel: String?,
        cancelable: Boolean,
        onClickListener: DialogInterface.OnClickListener?
    ) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(positiveLabel, onClickListener)
            .setNegativeButton(
                negativeLabel
            ) { dialogInterface: DialogInterface, i: Int -> dialogInterface.dismiss() }
            .setCancelable(cancelable)
            .show()
    }


    override fun onDestroyView() {
        loadingProgressBar?.cancel()
        loadingProgressBar = null
        //  recyclerAdapter = null
        super.onDestroyView()
    }


}