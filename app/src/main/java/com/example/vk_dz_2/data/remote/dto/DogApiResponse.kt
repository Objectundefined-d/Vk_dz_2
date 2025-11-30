package com.example.vk_dz_2.data.remote.dto

import com.squareup.moshi.Json

data class DogApiResponse(
    @Json(name = "message") val message: List<String>,
    @Json(name = "status") val status: String
)