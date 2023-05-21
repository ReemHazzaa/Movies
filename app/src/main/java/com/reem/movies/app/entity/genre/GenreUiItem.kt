package com.reem.movies.app.entity.genre

import androidx.databinding.library.baseAdapters.BR
import com.reem.movies.R
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.ui.home.viewHolder.genre.GenreViewHolder


data class GenreUiItem(
    val name: String
) : Listable {
    override val listItemType: HolderClass =
        HolderClass(GenreViewHolder::class.java, R.layout.item_genre)
    override val variableId: Int = BR.item
}
