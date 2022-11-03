package com.example.destest.core.main.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.destest.R
import com.example.destest.ui.theme.White

private val styles = object {
    val title = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = White,
        textAlign = TextAlign.Center
    )
}

private val strings = object : Any(){
    @Composable
    fun connection() = stringResource(id = R.string.main_connection)
}

@Composable
fun ConnectionProblemInfo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .background(Color.Red)
        )
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = strings.connection(),
            style = styles.title,
        )
    }
}