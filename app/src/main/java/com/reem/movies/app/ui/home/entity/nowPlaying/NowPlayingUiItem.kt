package com.reem.movies.app.ui.home.entity.nowPlaying

import androidx.databinding.library.baseAdapters.BR
import com.reem.movies.R
import com.reem.movies.app.binding.genericadapter.HolderClass
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.ui.home.viewHolder.nowPlaying.NowPlayingViewHolder
import com.reem.movies.app.ui.home.viewHolder.upcoming.UpcomingViewHolder

class NowPlayingUiItem(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val voteAvg: String,
) : Listable {
    override val listItemType: HolderClass =
        HolderClass(NowPlayingViewHolder::class.java, R.layout.item_now_playing)
    override val variableId: Int = BR.item
}