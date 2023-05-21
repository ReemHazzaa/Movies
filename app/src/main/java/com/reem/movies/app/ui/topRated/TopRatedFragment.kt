package com.reem.movies.app.ui.topRated

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
import com.reem.movies.app.extensions.updateStatusBarColor
import com.reem.movies.app.ui.topRated.entity.TopRatedUiItem
import com.reem.movies.databinding.FragmentTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : BaseFragment<TopRatedViewModel, FragmentTopRatedBinding>() {
    override val layoutResId: Int = R.layout.fragment_top_rated
    override val mViewModel: TopRatedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTopRated()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().updateStatusBarColor(R.color.grey_E3E2E5)
        viewDataBinding.apply {
            setVariable(BR.viewModel, mViewModel)

            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = true
                getTopRated()
                swipeRefresh.isRefreshing = false
            }

            rvResult.adapter =
                GeneralListAdapter(context = requireContext(), onItemClickCallback = object :
                    OnItemClickCallback {
                    override fun onItemClicked(view: View, listableItem: Listable, position: Int) {
                        val itemId = (listableItem as TopRatedUiItem).id
                        val action =
                            TopRatedFragmentDirections.actionNavigationTopRatedToMovieDetailsFragment(
                                itemId
                            )
                        findNavController().navigate(action)
                    }
                })
        }
    }

    private fun getTopRated() {
        mViewModel.getTopRated(1)
    }

}