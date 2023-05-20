package com.reem.movies.app.base.liveDataUtils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ObserveOnceLiveData<T> : MutableLiveData<T?>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        super.observe(owner) { t ->
            if (t != null) {
                removeObserver(observer)
                observer.onChanged(t)
            }
        }
    }
}