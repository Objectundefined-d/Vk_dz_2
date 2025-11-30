package com.example.vk_dz_2.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vk_dz_2.data.remote.ApiService
import com.example.vk_dz_2.data.remote.dto.DogImageDto
import com.example.vk_dz_2.domain.model.Image

class ImagePagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Image>() {

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            val page = params.key ?: 1

            val response = apiService.getImages()

            val images = response.message.mapIndexed { index, imageUrl ->
                DogImageDto(
                    imageUrl = imageUrl,
                    index = (page - 1) * 20 + index
                ).toImage()
            }

            LoadResult.Page(
                data = images,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}