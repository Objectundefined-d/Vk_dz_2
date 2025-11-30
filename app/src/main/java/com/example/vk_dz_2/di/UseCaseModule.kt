package com.example.vk_dz_2.di

import com.example.vk_dz_2.domain.repository.ImageRepository
import com.example.vk_dz_2.domain.usecase.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetImagesUseCase(repository: ImageRepository): GetImagesUseCase {
        return GetImagesUseCase(repository)
    }
}