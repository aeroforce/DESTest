package com.example.destest.feature.story.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.destest.R

private val dimens = object {
    val posterHeight = 240.dp
    val ctaHeight = 50.dp
    val ctaPaddingTop = 12.dp
    val ctaPaddingHorizontal = 8.dp
}

private val ids = object {
    val back = "Back"
    val share = "Share"
}

@Composable
fun StoryPoster(
    modifier: Modifier = Modifier,
    image: String,
    description: String,
    onBackClick :() -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(dimens.posterHeight),
            model = image,
            contentDescription = description,
            contentScale = ContentScale.Crop,
        )
        Image(
            modifier = Modifier
                .height(dimens.ctaHeight)
                .align(Alignment.TopStart)
                .padding(top = dimens.ctaPaddingTop, start = dimens.ctaPaddingHorizontal)
                .clickable { onBackClick.invoke() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = ids.back,
        )
        Image(
            modifier = Modifier
                .height(dimens.ctaHeight)
                .align(Alignment.TopEnd)
                .padding(top = dimens.ctaPaddingTop, end = dimens.ctaPaddingHorizontal),
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = ids.share,
        )
    }
}
