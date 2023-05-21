package com.reem.movies.app.binding.bindingAdapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.reem.movies.R
import com.reem.movies.app.base.Constants.MOVIE_DB_IMAGE_BASE_URL

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadImageWithGlide")
    fun ImageView.loadImageFromMovieDBWithGlide(url: String?) {
        try {
            Glide
                .with(this.rootView)
                .load("$MOVIE_DB_IMAGE_BASE_URL$url")
//                .centerCrop()
                .error(R.drawable.baseline_error_outline_24)
                .placeholder(R.drawable.baseline_downloading_24)
                .into(this)

        } catch (e: java.lang.Exception) {
            Log.e("Glide", e.toString())
        }
    }

}