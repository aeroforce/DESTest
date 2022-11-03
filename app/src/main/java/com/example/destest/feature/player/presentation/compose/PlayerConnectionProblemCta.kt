package com.example.destest.feature.player.presentation.compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.destest.R

private val strings = object : Any() {
    @Composable
    fun ctaButton() = stringResource(id = R.string.player_cta_button)
}

@Composable
fun PlayerConnectionProblemCta(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        content = { Text(strings.ctaButton()) }
    )
}
