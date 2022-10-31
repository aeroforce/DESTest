package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.destest.ui.theme.DarkBlue
import com.example.destest.ui.theme.White

private val styles = object {
    fun headerStyle() = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        color = White,
    )
}

@Composable
fun Header(title: String,) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(DarkBlue),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = styles.headerStyle(),
        )
    }
}
