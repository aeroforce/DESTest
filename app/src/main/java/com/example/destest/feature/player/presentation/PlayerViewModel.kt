package com.example.destest.feature.player.presentation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destest.core.main.AppRouteParameter
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.player.domain.usecase.GetVideo
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getVideo: GetVideo,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = mutableStateOf(PlayerState())
    val state: State<PlayerState> = _state

    private val _eventFlow = MutableSharedFlow<PlayerViewModel.UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val videoId = savedStateHandle.get<Int>(AppRouteParameter.PLAYER.param) ?: 0
        fetchVideo(videoId)
    }

    // private var video: Video by mutableStateOf(Video.Empty)
    var mediaItem: MediaItem by mutableStateOf(MediaItem.EMPTY)
        private set
    lateinit var player: ExoPlayer
    private val coroutineScope = MainScope()

    fun fetchVideo(id: Int) {
//        coroutineScope.launch {
//            getVideo.execute(id)?.let {
//                video = it
//                mediaItem = MediaItem.fromUri(it.url)
//                initVideoPlayer(context, it)
//            }
//        }

        viewModelScope.launch {
            getVideo(id)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                video = result.data ?: Video.Empty,
                                mediaItem = result.data?.url?.let { MediaItem.fromUri(it) } ?: mediaItem,
                                isLoading = false,
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                video = result.data ?: Video.Empty,
                                isLoading = false,
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                video = result.data ?: Video.Empty,
                                isLoading = true,
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun initVideoPlayer(context: Context, video: Video) {
        player = ExoPlayer.Builder(context).build()
        player.setMediaItem(MediaItem.fromUri(video.url))
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}
