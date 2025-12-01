package com.example.vk_dz_2.data.remote.dto

import com.squareup.moshi.Json
import com.example.vk_dz_2.domain.model.Image

data class ImageDto(
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "breeds") val breeds: List<BreedDto>? = emptyList()
) {
    fun toImage(): Image {
        return Image(
            id = id,
            url = url,
            width = width,
            height = height,
            author = breeds?.firstOrNull()?.name ?: "Cat"
        )
    }
}

data class BreedDto(
    @Json(name = "name") val name: String?,
    @Json(name = "origin") val origin: String?
)