package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.destest.R

private val dimens = object {
    val playOverlayHeight = 54.dp
}

private val ids = object {
    val playOverlay = "PlayOverlay"
}

@Composable
fun PlayOverlay(modifier: Modifier = Modifier) {
    Box(modifier = modifier.height(dimens.playOverlayHeight)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = ids.playOverlay,
        )
    }
}
