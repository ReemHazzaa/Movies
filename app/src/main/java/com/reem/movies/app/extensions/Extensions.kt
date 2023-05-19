package com.reem.movies.app.extensions

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes

fun Activity.updateStatusBarColor(@ColorRes colorId: Int, isLight: Boolean = true) {

    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = loadColor(colorId)
    if (isLight) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}

fun Context.loadColor(@ColorRes id: Int): Int {
    return try {
        getColor(id)
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
        0
    }
}

const val TAG = "Extensions"