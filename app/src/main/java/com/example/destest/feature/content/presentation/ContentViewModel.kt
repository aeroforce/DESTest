package com.example.destest.feature.content.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.usecase.GetContent
import com.example.destest.feature.content.domain.usecase.GetStories
import com.example.destest.feature.content.domain.usecase.GetVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getStories: GetStories,
    private val getVideos: GetVideos,
    private val getContent: GetContent,
) : ViewModel() {

    private val _state = mutableStateOf(ContentState())
    val state: State<ContentState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        fetchContent()
        // fetchStories()
        // fetchVideos()
    }

    fun getHeader() = "FEATURED"

    private fun fetchContent() {
        Log.d("AERODEBUG", "vm.fetchContent()")
        viewModelScope.launch {
            getContent.invoke()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                content = result.data ?: emptyList(),
                                isLoading = false
                            )
                            Log.d("AERODEBUG", "vm.fetchContent().success result: ${result.data}")
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                content = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown error when content"))
                            Log.d("AERODEBUG", "vm.fetchContent().error result: ${result.message}")
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                content = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun fetchVideos() {
        viewModelScope.launch {
            getVideos.invoke()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                videos = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                videos = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown error"))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                videos = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun fetchStories() {
        viewModelScope.launch {
            getStories.invoke()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                stories = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                stories = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown error"))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                stories = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}
