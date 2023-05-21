package com.reem.movies.app.binding.genericadapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reem.movies.domain.usecase.baseUseCase.BaseUseCase

class GenericPagingSource(
    private val useCase: BaseUseCase<Int, List<Listable>>,
    private var startingPage: Int = 1
) :
    PagingSource<Int, Listable>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Listable> {
        try {
            val currentLoadingPageKey = params.key ?: startingPage
            val response = useCase.execute(currentLoadingPageKey) ?: emptyList()
            val responseData = mutableListOf<Listable>()
            val endOfPaginationReached = response.isEmpty()
            responseData.addAll(response)
            val prevKey =
                if (currentLoadingPageKey == startingPage) null else currentLoadingPageKey - 1
            val nextKey = if (endOfPaginationReached) null else currentLoadingPageKey + 1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Listable>): Int {
        return startingPage
    }

}