package com.example.vk_dz_2.data.remote

import com.example.vk_dz_2.data.remote.dto.DogApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/breeds/image/random/20")
    suspend fun getImages(): DogApiResponse
}