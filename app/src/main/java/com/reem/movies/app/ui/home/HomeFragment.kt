package com.reem.movies.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.reem.movies.R
import com.reem.movies.app.base.baseUi.BaseFragment
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.adapter.GeneralListAdapter
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.ui.home.entity.genre.GenreUiItem
import com.reem.movies.app.ui.home.entity.nowPlaying.NowPlayingUiItem
import com.reem.movies.app.ui.home.entity.popular.PopularUiItem
import com.reem.movies.app.ui.home.entity.upcoming.UpcomingUiItem
import com.reem.movies.data.remote.networkLayer.NetworkManager
import com.reem.movies.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val layoutResId: Int = R.layout.fragment_home
    override val mViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadHome()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.apply {
            setVariable(BR.viewModel, mViewModel)

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = true
                loadHome()
                swipeRefresh.isRefreshing = false
            }

            rvNowPlaying.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as NowPlayingUiItem).id
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
                        findNavController().navigate(action)
                    }
                })

            rvUpcoming.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as UpcomingUiItem).id
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
                        findNavController().navigate(action)
                    }
                })

            rvPopular.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as PopularUiItem).id
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
                        findNavController().navigate(action)
                    }
                })

            rvGenres.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val item = (listableItem as GenreUiItem)
                        val action =
                            HomeFragmentDirections.actionNavigationHomeToMoviesOfGenreFragment(
                                item.id,
                                item.name
                            )
                        findNavController().navigate(action)
                    }
                })
        }
    }

    private fun loadHome() {
        mViewModel.apply {
            getUpcoming(1)
            getNowPlaying(1)
            getGenres()

            lifecycleScope.launch(mViewModel.getExceptionHandler()) {

                if (!networkManager.isNetworkAvailable()) {
                    viewDataBinding.rvPopular.adapter =
                        GeneralListAdapter(
                            context = requireContext(),
                            onItemClickCallback = object :
                                OnItemClickCallback {
                                override fun onItemClicked(
                                    view: View,
                                    listableItem: Listable,
                                    position: Int
                                ) {
                                    val itemId = (listableItem as PopularUiItem).id
                                    val action =
                                        HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(
                                            itemId
                                        )
                                    findNavController().navigate(action)
                                }
                            })
                    mViewModel.readCachedPopular().observe(viewLifecycleOwner) { it ->
                        val list = it.map { movieItem ->
                            PopularUiItem(
                                movieItem.id,
                                movieItem.title,
                                movieItem.imageUrl,
                                movieItem.voteAvg
                            )
                        }
                        mViewModel.popularLiveData.value = list
                    }
                } else
                    if (networkManager.isNetworkAvailable()) getPopular(1)
            }
        }
    }
}