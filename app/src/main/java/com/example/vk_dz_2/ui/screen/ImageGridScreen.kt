package com.example.vk_dz_2.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.vk_dz_2.domain.model.Image
import com.example.vk_dz_2.ui.component.ErrorScreen
import com.example.vk_dz_2.ui.component.ImageCard
import com.example.vk_dz_2.ui.component.LoadingIndicator
import com.example.vk_dz_2.ui.viewmodel.ImageViewModel

@Composable
fun ImageGridScreen(viewModel: ImageViewModel) {
    val context = LocalContext.current
    val images = viewModel.images.collectAsLazyPagingItems()

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (images.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingIndicator()
                }
                is LoadState.Error -> {
                    ErrorScreen(
                        message = "Не удалось загрузить изображения",
                        onRetry = { images.retry() }
                    )
                }
                else -> {
                    ImageGrid(
                        images = images,
                        onImageClick = { position ->
                            Toast.makeText(
                                context,
                                "Изображение #$position",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            }

            if (images.loadState.append is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ImageGrid(
    images: LazyPagingItems<Image>,
    onImageClick: (Int) -> Unit
) {
    if (images.itemCount == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Нет загруженных изображений")
        }
        return
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize(),
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
                    onClick = { onImageClick(index + 1) }
                )
            }
        }
    }
}