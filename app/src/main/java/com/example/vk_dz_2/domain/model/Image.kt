package com.example.vk_dz_2.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: String,
    val url: String,
    val title: String,
    val thumbnailUrl: String
) : Parcelable