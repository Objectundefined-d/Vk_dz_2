package com.example.vk_dz_2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.vk_dz_2.data.paging.ImagePagingSource
import com.example.vk_dz_2.domain.model.Image
import kotlinx.coroutines.flow.Flow

class ImageViewModel : ViewModel() {

    val images: Flow<PagingData<Image>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { ImagePagingSource() }
    ).flow.cachedIn(viewModelScope)
}