package com.example.vk_dz_2.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vk_dz_2.domain.model.Image

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
    val cachedAt: Long = System.currentTimeMillis()
) {
    fun toImage(): Image {
        return Image(
            id = id,
            author = author,
            width = width,
            height = height,
            url = url,
            downloadUrl = downloadUrl
        )
    }
}

fun Image.toEntity(): ImageEntity {
    return ImageEntity(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl
    )
}

fun List<Image>.toEntityList(): List<ImageEntity> {
    return map { it.toEntity() }
}

fun List<ImageEntity>.toImageList(): List<Image> {
    return map { it.toImage() }
}
