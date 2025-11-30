package com.example.vk_dz_2.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.vk_dz_2.data.cache.AppDatabase
import com.example.vk_dz_2.data.paging.ImagePagingSource
import com.example.vk_dz_2.domain.model.Image
import com.example.vk_dz_2.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : ImageRepository {

    override fun getImages(): Flow<PagingData<Image>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { ImagePagingSource() }
        ).flow
    }

    override suspend fun refresh() {
        // For simple implementation, we'll rely on PagingSource invalidation
        // In advanced version, we could clear cache here
    }
}