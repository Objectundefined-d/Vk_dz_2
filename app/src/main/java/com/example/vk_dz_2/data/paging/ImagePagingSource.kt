package com.example.vk_dz_2.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vk_dz_2.data.api.RetrofitClient
import com.example.vk_dz_2.domain.model.Image

class ImagePagingSource : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            val page = params.key ?: 1
            val response = RetrofitClient.imageApiService.getImages(
                page = page,
                limit = params.loadSize
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}