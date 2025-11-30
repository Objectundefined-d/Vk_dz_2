package com.example.vk_dz_2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vk_dz_2.data.paging.ImagePagingSource
import com.example.vk_dz_2.data.remote.ApiService
import com.example.vk_dz_2.domain.model.Image
import com.example.vk_dz_2.domain.repository.ImageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ImageRepository {

    private val pagingScope = CoroutineScope(Dispatchers.IO)

    override fun getImagesPagingData(): Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(apiService) }
        ).flow.cachedIn(pagingScope)
    }

    override fun getImages(): Flow<List<Image>> {
        throw NotImplementedError("Use getImagesPagingData instead")
    }

    override suspend fun loadMoreImages(page: Int): List<Image> {
        throw NotImplementedError("Use getImagesPagingData instead")
    }

    override suspend fun refreshImages(): List<Image> {
        throw NotImplementedError("Use getImagesPagingData instead")
    }
}