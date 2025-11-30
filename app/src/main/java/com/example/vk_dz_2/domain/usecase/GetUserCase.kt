package com.example.vk_dz_2.domain.usecase

import androidx.paging.PagingData
import com.example.vk_dz_2.domain.model.Image
import com.example.vk_dz_2.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke(): Flow<PagingData<Image>> {
        return repository.getImages()
    }
}