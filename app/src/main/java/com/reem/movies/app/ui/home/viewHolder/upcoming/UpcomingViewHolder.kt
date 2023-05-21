package com.reem.movies.app.ui.home.viewHolder.upcoming

import androidx.databinding.ViewDataBinding
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.binding.genericadapter.viewholder.BaseViewHolder
import com.reem.movies.app.ui.home.entity.upcoming.UpcomingUiItem

class UpcomingViewHolder(val binding: ViewDataBinding, onItemClickCallback: OnItemClickCallback) :
    BaseViewHolder(binding, onItemClickCallback) {
    init {
        attachClickListener(binding.root)
    }

    override fun draw(listable: Listable) {
        super.draw(listable)
        val current = listable as UpcomingUiItem
    }
}