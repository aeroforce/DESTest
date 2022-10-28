package com.example.destest.feature.player.presentation.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.destest.core.kotlin.pass
import com.example.destest.core.main.compose.OnLifecycleEvent
import com.example.destest.feature.player.presentation.PlayerViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlayerScreen(
    id: Int,
) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<PlayerViewModel>()
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PlayerViewModel.UIEvent.ShowSnackBar -> {
                    Log.d("AERODEBUG", event.message)
                }
            }
        }
    }

    if (state.mediaItem != MediaItem.EMPTY) {
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
}
