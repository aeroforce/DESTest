package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.destest.feature.content.domain.model.Video

private val dimens = object {
    val titlePaddingHorizontal = 10.dp
    val viewsPadding = 10.dp
}

private val strings = object {
    val views = "views"
}

@Composable
fun VideoItemInfo(
    modifier: Modifier = Modifier,
    video: Video,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = dimens.titlePaddingHorizontal),
            text = video.title,
            style = ItemInfoStyles.titleStyle,
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(dimens.viewsPadding),
            text = "${video.views} ${strings.views}",
            style = ItemInfoStyles.footerStyle,
        )
    }
}
