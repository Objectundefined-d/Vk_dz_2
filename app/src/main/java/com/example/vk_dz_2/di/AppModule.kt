package com.example.vk_dz_2.di

import android.content.Context
import com.example.vk_dz_2.data.cache.AppDatabase
import com.example.vk_dz_2.data.repository.ImageRepositoryImpl
import com.example.vk_dz_2.domain.repository.ImageRepository
import com.example.vk_dz_2.domain.usecase.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideImageRepository(database: AppDatabase): ImageRepository {
        return ImageRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun provideGetImagesUseCase(repository: ImageRepository): GetImagesUseCase {
        return GetImagesUseCase(repository)
    }
}