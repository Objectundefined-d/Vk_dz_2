package com.example.vk_dz_2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vk_dz_2.domain.model.Image

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val author: String = "Cat",
    val cachedAt: Long = System.currentTimeMillis()
) {
    fun toImage(): Image {
        return Image(
            id = id,
            url = url,
            width = width,
            height = height,
            author = author
        )
    }
}

fun Image.toEntity(): ImageEntity {
    return ImageEntity(
        id = id,
        url = url,
        width = width,
        height = height,
        author = author
    )
}

fun List<Image>.toEntityList(): List<ImageEntity> {
    return map { it.toEntity() }
}

fun List<ImageEntity>.toImageList(): List<Image> {
    return map { it.toImage() }
}