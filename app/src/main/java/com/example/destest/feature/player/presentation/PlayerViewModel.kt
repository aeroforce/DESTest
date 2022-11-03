package com.example.destest.feature.player.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destest.core.ErrorMessage
import com.example.destest.core.main.AppRouteParameter
import com.example.destest.core.service.appstate.AppStateService
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.player.domain.usecase.GetVideo
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getVideo: GetVideo,
    private val appStateService: AppStateService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(PlayerState())
        private set

    init {
        val videoId = savedStateHandle.get<Int>(AppRouteParameter.PLAYER.param) ?: 0
        fetchVideo(videoId)
    }

    var mediaItem: MediaItem by mutableStateOf(MediaItem.EMPTY)
        private set

    private fun fetchVideo(id: Int) {

        viewModelScope.launch {
            getVideo(id)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                video = result.data ?: Video.Empty,
                                mediaItem = result.data?.url?.let { MediaItem.fromUri(it) } ?: mediaItem,
                            )
                            appStateService.setBusy(false)
                        }
                        is Resource.Error -> {
                            appStateService.setBusy(false)
                            state = state.copy(
                                video = result.data ?: Video.Empty,
                                isConnectionProblem = true,
                            )
                            result.message?.let { message ->
                                if (message == ErrorMessage.IO_EXCEPTION.message) {
                                    appStateService.setConnectionProblem(true)
                                }
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                video = result.data ?: Video.Empty,
                            )
                            appStateService.setBusy(true)
                        }
                    }
                }.launchIn(this)
        }
    }
}
