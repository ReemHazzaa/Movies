package com.reem.movies.app.ui.topRated.entity

import androidx.databinding.library.baseAdapters.BR
import com.reem.movies.R
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.ui.topRated.viewHolder.TopRatedViewHolder

data class TopRatedUiItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
) : Listable {
    override val listItemType: HolderClass =
        HolderClass(TopRatedViewHolder::class.java, R.layout.item_top_rated)
    override val variableId: Int = BR.item
}
