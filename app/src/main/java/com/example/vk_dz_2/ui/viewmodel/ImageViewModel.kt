package com.example.vk_dz_2.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vk_dz_2.domain.model.Image
import com.example.vk_dz_2.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    val images: Flow<PagingData<Image>> = getImagesUseCase()
        .cachedIn(viewModelScope)

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun onRetry() {
        error.value = null
    }
}