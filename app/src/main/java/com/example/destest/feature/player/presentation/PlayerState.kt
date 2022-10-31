package com.example.destest.feature.player.presentation

import com.example.destest.feature.content.domain.model.Video
import com.google.android.exoplayer2.MediaItem

data class PlayerState(
    val video: Video = Video.Empty,
    val mediaItem: MediaItem = MediaItem.EMPTY,
    val isLoading: Boolean = false,
    val isConnectionProblem: Boolean = false,
)
