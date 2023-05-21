package com.reem.movies.app.ui.moviesOfGenre.entity

import androidx.databinding.library.baseAdapters.BR
import com.reem.movies.R
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.ui.moviesOfGenre.viewHolder.GenreMovieViewHolder

data class GenreMovieUiItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
) : Listable {
    override val listItemType: HolderClass =
        HolderClass(GenreMovieViewHolder::class.java, R.layout.item_genre_movie)
    override val variableId: Int = BR.item
}
