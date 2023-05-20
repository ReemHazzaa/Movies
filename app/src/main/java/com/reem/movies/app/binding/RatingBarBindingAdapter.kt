package com.reem.movies.app.binding

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

object RatingBarBindingAdapter {

    @JvmStatic
    @BindingAdapter("rate")
    fun RatingBar.rate(value: String?) {
        rating = value?.toFloat() ?: 0f
    }
}