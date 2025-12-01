package com.example.vk_dz_2.data.remote.api

import com.example.vk_dz_2.data.remote.dto.ImageDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageApi {
    @GET("images/search")
    suspend fun getImages(
        @Header("x-api-key") apiKey: String = "live_7KGzHSFfCPUUxWNUDMFGxWWF3jYSdFmB8WpZTlBWJEyNc2If9QgFZTLddIduAsIW",
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20,
        @Query("order") order: String = "DESC",
        @Query("mime_types") mimeTypes: String = "jpg,png",
        @Query("size") size: String = "med"
    ): List<ImageDto>
}