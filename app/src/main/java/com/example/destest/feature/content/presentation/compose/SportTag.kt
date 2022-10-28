package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.destest.ui.theme.DarkBlue
import com.example.destest.ui.theme.White

@Composable
fun SportTag(modifier: Modifier, sport: String) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(DarkBlue)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            text = sport.uppercase(),
            color = White,
        )
    }
}
