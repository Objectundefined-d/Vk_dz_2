package com.example.vk_dz_2.data.remote.dto

import com.example.vk_dz_2.domain.model.Image

data class DogImageDto(
    val imageUrl: String,
    val index: Int
) {
    fun toImage() = Image(
        id = index.toString(),
        url = imageUrl,
        title = "Dog Photo #$index",
        thumbnailUrl = imageUrl
    )
}