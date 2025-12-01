package com.example.vk_dz_2.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.example.vk_dz_2.data.remote.api.ImageApi

object RetrofitClient {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"
    private const val API_KEY = "live_7KGzHSFfCPUUxWNUDMFGxWWF3jYSdFmB8WpZTlBWJEyNc2If9QgFZTLddIduAsIW"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)
                .addHeader("User-Agent", "ImageGalleryApp/1.0")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val imageApi: ImageApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(ImageApi::class.java)
    }
}