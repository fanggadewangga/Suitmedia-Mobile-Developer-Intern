package com.fangga.suitmediamobiledeveloperintern2023.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.api.ApiService
import com.fangga.suitmediamobiledeveloperintern2023.data.source.remote.response.UserResponse
import com.fangga.suitmediamobiledeveloperintern2023.util.Constant

class UserPagingSource(
    private val apiService: ApiService,
): PagingSource<Int, UserResponse>() {
    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val page = params.key ?: Constant.INITIAL_PAGE_INDEX
            val response = apiService.fetchUsers(page)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == Constant.INITIAL_PAGE_INDEX) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}