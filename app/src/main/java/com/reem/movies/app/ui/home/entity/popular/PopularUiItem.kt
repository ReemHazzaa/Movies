package com.reem.movies.app.ui.home.entity.popular

import androidx.databinding.library.baseAdapters.BR
import com.reem.movies.R
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.ui.home.viewHolder.popular.PopularViewHolder

class PopularUiItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
) : Listable {
    override val listItemType: HolderClass =
        HolderClass(PopularViewHolder::class.java, R.layout.item_popular)
    override val variableId: Int = BR.item
}