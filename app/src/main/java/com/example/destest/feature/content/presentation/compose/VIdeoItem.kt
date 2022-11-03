package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.ui.theme.White

private val dimens = object {
    val roundedCornerShapeSize = 8.dp
    val videoInfoPaddingTop = 200.dp
    val sportTagPaddingStart = 10.dp
    val sportTagPaddingTop = 165.dp
    val playOverlayPaddingTop = 65.dp
}

@Composable
fun VideoItem(video: Video, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(dimens.roundedCornerShapeSize))
            .background(White)
            .clickable { onClick.invoke() }
    ) {
        ItemPoster(
            modifier = Modifier.align(Alignment.TopCenter),
            video.thumb,
            video.title
        )
        VideoItemInfo(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(top = dimens.videoInfoPaddingTop),
            video = video,
        )
        SportTag(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = dimens.sportTagPaddingStart, top = dimens.sportTagPaddingTop),
            video.sport,
        )
        PlayOverlay(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimens.playOverlayPaddingTop)
        )
    }
}
