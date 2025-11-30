package com.example.vk_dz_2.domain.repository

import androidx.paging.PagingData
import com.example.vk_dz_2.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImagesPagingData(): Flow<PagingData<Image>>

    fun getImages(): Flow<List<Image>>

    suspend fun loadMoreImages(page: Int): List<Image>
    suspend fun refreshImages(): List<Image>
}