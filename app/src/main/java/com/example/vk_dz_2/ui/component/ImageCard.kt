package com.example.vk_dz_2.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.vk_dz_2.domain.model.Image

@Composable
fun ImageCard(
    image: Image,
    index: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(image.url)
            .crossfade(true)
            .build(),
        contentDescription = "Image by ${image.author}",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(image.width.toFloat() / image.height.toFloat())
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(index) }
            .padding(4.dp)
    )
}