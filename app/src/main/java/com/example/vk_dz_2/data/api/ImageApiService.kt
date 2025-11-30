package com.example.vk_dz_2.data.api

import com.example.vk_dz_2.domain.model.Image
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {

    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20
    ): List<Image>
}