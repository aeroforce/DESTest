package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ContentItemPoster(
    modifier: Modifier = Modifier,
    url: String,
    description: String,
) {
    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(180.dp),
            model = url,
            contentDescription = description,
            contentScale = ContentScale.Crop,
        )
    }
}
