package com.reem.movies.app.ui.home.entity.upcoming

import androidx.databinding.library.baseAdapters.BR
import com.reem.movies.R
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.ui.home.viewHolder.upcoming.UpcomingViewHolder

data class UpcomingUiItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
) : Listable {
    override val listItemType: HolderClass =
        HolderClass(UpcomingViewHolder::class.java, R.layout.item_upcoming)
    override val variableId: Int = BR.item
}
