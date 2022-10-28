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

@Composable
fun PlayButton(modifier: Modifier = Modifier) {
    Box(modifier = modifier.height(54.dp)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = "PlayButton",
        )
    }
}
