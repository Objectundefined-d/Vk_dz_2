package com.example.vk_dz_2.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.vk_dz_2.R
import com.example.vk_dz_2.domain.model.Image
import com.example.vk_dz_2.ui.component.ErrorScreen
import com.example.vk_dz_2.ui.component.ImageCard
import com.example.vk_dz_2.ui.component.LoadingIndicator
import com.example.vk_dz_2.ui.component.PaginationLoader
import com.example.vk_dz_2.ui.viewmodel.ImageViewModel
import kotlinx.coroutines.launch

@Composable
fun ImageGridScreen(
    viewModel: ImageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val images = viewModel.images.collectAsLazyPagingItems()
    val isLoading = viewModel.isLoading
    val error = viewModel.error
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Show snackbar on error
    LaunchedEffect(error.value) {
        error.value?.let { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading.value -> {
                    LoadingIndicator()
                }
                error.value != null && images.itemCount == 0 -> {
                    ErrorScreen(
                        message = error.value,
                        onRetry = viewModel::onRetry
                    )
                }
                else -> {
                    ImageGrid(
                        images = images,
                        onImageClick = { index ->
                            scope.launch {
                                val message = context.getString(R.string.image_number, index + 1)
                                snackbarHostState.showSnackbar(message)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun ImageGrid(
    images: LazyPagingItems<Image>,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        state = gridState,
        modifier = modifier,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(8.dp)
    ) {
        items(
            count = images.itemCount,
            key = images.itemKey { it.id }
        ) { index ->
            val image = images[index]
            if (image != null) {
                ImageCard(
                    image = image,
                    index = index,
                    onClick = { onImageClick(index) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Show loading indicator at the bottom when loading more
        if (images.loadState.append is androidx.paging.LoadState.Loading) {
            item {
                PaginationLoader()
            }
        }
    }
}