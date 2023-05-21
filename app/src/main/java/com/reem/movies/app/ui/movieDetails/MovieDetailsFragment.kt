package com.reem.movies.app.ui.movieDetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.button.MaterialButton
import com.reem.movies.R
import com.reem.movies.app.base.baseUi.BaseFragment
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.adapter.GeneralListAdapter
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.ui.favorites.entity.FavMovieItem
import com.reem.movies.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel, FragmentMovieDetailsBinding>() {

    override val layoutResId: Int = R.layout.fragment_movie_details
    override val mViewModel: MovieDetailsViewModel by viewModels()

    private val args: MovieDetailsFragmentArgs by navArgs()

    private var isFavMovie = false
    private var courseWishlistEdId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getMovieDetails(args.movieID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.apply {
            setVariable(BR.viewModel, mViewModel)

            isMovieInFav(btAddToFav)

            btAddToFav.setOnClickListener {
                if (isFavMovie) removeFromFavorites()
                else addMovieToFavorites()
            }

            rvGenres.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {

                    }
                })

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = true
                mViewModel.getMovieDetails(args.movieID)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun isMovieInFav(button: MaterialButton) {
        lifecycleScope.launch {
            mViewModel.readAllFav().observe(viewLifecycleOwner) { entity ->
                try {
                    val movie = entity.find { it.id == args.movieID }
                    if (movie != null) {   // movie is in fav
                        changeWishListButtonState(
                            button,
                            getString(R.string.in_fav),
                            activity?.getDrawable(R.drawable.ic_favorite_filled)!!
                        )
                        courseWishlistEdId = movie.id
                        isFavMovie = true
                    } else {
                        changeWishListButtonState(
                            button,
                            getString(R.string.add_to_favorites),
                            activity?.getDrawable(R.drawable.ic_favorite)!!
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, e.message.toString(), e)
                }
            }
        }
    }

    private fun addMovieToFavorites() {
        val current = mViewModel.detailsLiveData.value
        mViewModel.addToFav(
            FavMovieItem(
                current!!.id, current.title, current.imageUrl, current.voteAvg
            )
        )
        changeWishListButtonState(
            viewDataBinding.btAddToFav,
            getString(R.string.in_fav),
            activity?.getDrawable(R.drawable.ic_favorite_filled)!!
        )
        isFavMovie = true
    }

    private fun removeFromFavorites() {
        val current = mViewModel.detailsLiveData.value
        mViewModel.removeItemFromFav(
            FavMovieItem(
                current!!.id, current.title, current.imageUrl, current.voteAvg
            )
        )

        changeWishListButtonState(
            viewDataBinding.btAddToFav,
            getString(R.string.add_to_favorites),
            activity?.getDrawable(R.drawable.ic_favorite)!!
        )
        isFavMovie = false
    }

    private fun changeWishListButtonState(
        button: MaterialButton,
        buttonTxt: String,
        drawable: Drawable
    ) {
        button.text = buttonTxt
        button.icon = drawable
    }
}

const val TAG = "MovieDetailsFragment"