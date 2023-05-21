package com.reem.movies.app.binding.genericadapter.listener

import android.view.View
import com.reem.movies.app.binding.genericadapter.Listable

interface OnItemClickCallback {
    fun onItemClicked(view: View, listableItem: Listable, position: Int)
}