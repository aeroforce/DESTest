package com.example.destest.feature.content.presentation.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.destest.ui.theme.Black

object ItemInfoStyles {
    fun titleStyle() = TextStyle(
        color = Black,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
    )

    fun footerStyle() = TextStyle(
        color = Color.LightGray,
        fontSize = 12.sp,
    )
}
