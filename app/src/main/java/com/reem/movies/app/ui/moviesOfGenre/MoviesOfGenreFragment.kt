package com.reem.movies.app.ui.moviesOfGenre

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.reem.movies.R
import com.reem.movies.app.base.baseUi.BaseFragment
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.adapter.GeneralListAdapter
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.extensions.updateStatusBarColor
import com.reem.movies.app.ui.moviesOfGenre.entity.GenreMovieUiItem
import com.reem.movies.databinding.FragmentMoviesOfGenreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesOfGenreFragment : BaseFragment<MoviesOfGenreViewModel, FragmentMoviesOfGenreBinding>() {
    override val layoutResId: Int = R.layout.fragment_movies_of_genre
    override val mViewModel: MoviesOfGenreViewModel by viewModels()

    private val args: MoviesOfGenreFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadMovies(args.genreId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().updateStatusBarColor(R.color.primaryColor, false)

        viewDataBinding.apply {
            setVariable(BR.viewModel, mViewModel)
            mViewModel.genreName.value = args.genreName

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = true
                loadMovies(args.genreId)
                swipeRefresh.isRefreshing = false
            }

            rvResult.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as GenreMovieUiItem).id
                        val action =
                            MoviesOfGenreFragmentDirections.actionMoviesOfGenreFragmentToMovieDetailsFragment(
                                itemId
                            )
                        findNavController().navigate(action)
                    }
                })
        }
    }

    private fun loadMovies(genreId: Int) {
        mViewModel.getMoviesOfGenre(genreId)
    }
}