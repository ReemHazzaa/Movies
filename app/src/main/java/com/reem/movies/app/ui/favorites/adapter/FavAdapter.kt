package com.reem.movies.app.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.reem.movies.R
import com.reem.movies.app.binding.bindingAdapter.ImageBindingAdapter.loadImageFromMovieDBWithGlide
import com.reem.movies.app.entity.favMovie.FavMovieItem
import com.reem.movies.app.ui.favorites.FavoritesFragmentDirections

class FavAdapter :
    RecyclerView.Adapter<FavAdapter.FavMoviesVH>() {

    private var data: List<FavMovieItem> = mutableListOf()

    class FavMoviesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvRate: TextView = itemView.findViewById(R.id.tv_rate)
        val ivMovie: ImageView = itemView.findViewById(R.id.iv_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMoviesVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fav, parent, false)
        return FavMoviesVH(view)
    }

    override fun onBindViewHolder(holder: FavMoviesVH, position: Int) {
        val current = data.get(position)
        holder.apply {
            tvTitle.text = current.title
            tvRate.text = current.voteAvg

            ivMovie.loadImageFromMovieDBWithGlide(current.imageUrl)

            itemView.setOnClickListener {
                val itemId = current.id
                val action =
                    FavoritesFragmentDirections.actionNavigationFavToMovieDetailsFragment(itemId)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(list: List<FavMovieItem>) {
        data = list
    }
}