package com.reem.movies.app.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.reem.movies.R
import com.reem.movies.app.base.baseUi.BaseFragment
import com.reem.movies.app.ui.favorites.adapter.FavAdapter
import com.reem.movies.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FavViewModel, FragmentFavoritesBinding>() {

    override val layoutResId: Int = R.layout.fragment_favorites
    override val mViewModel: FavViewModel by viewModels()

    private val favAdapter = FavAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.apply {
            setVariable(BR.viewModel, mViewModel)
        }

        lifecycleScope.launch(mViewModel.getExceptionHandler()) {
            mViewModel.readAllFav().observe(viewLifecycleOwner) { fav ->
                favAdapter.setData(fav)
                viewDataBinding.rvResult.adapter = favAdapter
            }
        }
    }
}