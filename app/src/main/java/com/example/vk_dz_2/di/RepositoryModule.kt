package com.example.vk_dz_2.di

import com.example.vk_dz_2.data.repository.ImageRepositoryImpl
import com.example.vk_dz_2.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(repositoryImpl: ImageRepositoryImpl): ImageRepository {
        return repositoryImpl
    }
}