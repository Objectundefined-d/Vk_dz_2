package com.example.vk_dz_2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vk_dz_2.domain.model.Image

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val title: String,
    val thumbnailUrl: String
)

fun ImageEntity.toImage() = Image(
    id = id,
    url = url,
    title = title,
    thumbnailUrl = thumbnailUrl
)

fun Image.toImageEntity() = ImageEntity(
    id = id,
    url = url,
    title = title,
    thumbnailUrl = thumbnailUrl
)