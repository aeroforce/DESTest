package com.example.destest.feature.player.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.destest.core.kotlin.pass
import com.example.destest.core.main.presentation.compose.OnLifecycleEvent
import com.example.destest.feature.player.presentation.PlayerViewModel
import com.example.destest.ui.theme.Black
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun PlayerScreen(onBackClick :() -> Unit = {}) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<PlayerViewModel>()
    val state = viewModel.state

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Black)
    ) {
        if (state.mediaItem != MediaItem.EMPTY && state.isConnectionProblem.not()) {
            val player = ExoPlayer.Builder(context).build()
            player.setMediaItem(MediaItem.fromUri(state.video.url))
            val playerView = StyledPlayerView(context)
            val playWhenReady by remember {
                mutableStateOf(true)
            }

            playerView.player = player
            playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

            LaunchedEffect(player) {
                player.prepare()
                player.playWhenReady = playWhenReady
            }

            AndroidView(factory = { playerView })
            OnLifecycleEvent { _, event ->
                when (event) {
                    Lifecycle.Event.ON_STOP -> player.pause()
                    Lifecycle.Event.ON_START -> player.play()
                    Lifecycle.Event.ON_DESTROY -> {
                        player.stop()
                        player.clearMediaItems()
                        player.release()
                    }
                    else -> pass
                }
            }
        }
        if (state.isConnectionProblem) {
            PlayerConnectionProblemCta(
                modifier = Modifier.align(Alignment.TopCenter),
                onClick = onBackClick,
            )
        }
    }
}
