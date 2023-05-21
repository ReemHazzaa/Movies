package com.reem.movies.app.ui.home

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.reem.movies.R
import com.reem.movies.app.base.baseUi.BaseFragment
import com.reem.movies.app.binding.genericadapter.Listable
import com.reem.movies.app.binding.genericadapter.adapter.GeneralListAdapter
import com.reem.movies.app.binding.genericadapter.listener.OnItemClickCallback
import com.reem.movies.app.entity.genre.GenreUiItem
import com.reem.movies.app.extensions.updateStatusBarColor
import com.reem.movies.app.ui.home.entity.nowPlaying.NowPlayingUiItem
import com.reem.movies.app.ui.home.entity.popular.PopularUiItem
import com.reem.movies.app.ui.home.entity.upcoming.UpcomingUiItem
import com.reem.movies.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val layoutResId: Int = R.layout.fragment_home
    override val mViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadHome()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().updateStatusBarColor(R.color.primaryColor, false)

        viewDataBinding.apply {
            setVariable(BR.viewModel, mViewModel)

//            searchView.setOnClickListener {
//                findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
//            }

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
                        val action = HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
                        findNavController().navigate(action)
                    }
                })

            rvUpcoming.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as UpcomingUiItem).id
                        val action = HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
                        findNavController().navigate(action)
                    }
                })

            rvPopular.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as PopularUiItem).id
                        val action = HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
                        findNavController().navigate(action)
                    }
                })

            rvGenres.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
//                        val itemId = (listableItem as GenreUiItem)
//                        val action = HomeFragmentDirections.actionNavigationHomeToMovieDetailsFragment(itemId)
//                        findNavController().navigate(action)
                    }
                })

//
//            lifecycleScope.launch {
//                mViewModel.readAllFav().observe(viewLifecycleOwner) { fav ->
//                    rvFav.adapter = FavHomeAdapter().apply { setData(fav) }
//                }
//            }
        }
    }

    private fun loadHome() {
        mViewModel.apply {
            getUpcoming(1)
            getNowPlaying(1)
            getPopular(1)
            getGenres()
        }
    }
}