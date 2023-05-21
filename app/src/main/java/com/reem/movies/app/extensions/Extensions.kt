package com.reem.movies.app.extensions

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes

fun Context.loadColor(@ColorRes id: Int): Int {
    return try {
        getColor(id)
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
        0
    }
}

const val TAG = "Extensions"