package com.example.vk_dz_2.domain.repository

import androidx.paging.PagingData
import com.example.vk_dz_2.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImages(): Flow<PagingData<Image>>
    suspend fun refresh()
}