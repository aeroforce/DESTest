package com.example.destest.feature.player.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.destest.ui.theme.Black
import com.example.destest.ui.theme.White

private val strings = object {
    val offline = "Offline mode"
}

private val styles = object {
    val offline = TextStyle(
        color = White,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )
}

@Composable
fun PlayerOffline() {
    Box(
        modifier = Modifier.fillMaxSize().background(Black)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = strings.offline,
            style = styles.offline,
        )
    }
}