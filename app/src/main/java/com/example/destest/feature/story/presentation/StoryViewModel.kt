package com.example.destest.feature.story.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destest.core.ErrorMessage
import com.example.destest.core.main.AppRouteParameter
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.story.domain.usecase.GetStory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val getStory: GetStory,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(StoryState())
    val state: State<StoryState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        val storyId = savedStateHandle.get<Int>(AppRouteParameter.STORY.param) ?: 0
        fetchStory(storyId)
    }

    private fun fetchStory(id: Int) {
        viewModelScope.launch {
            getStory(id)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                story = result.data ?: Story.Empty,
                                isLoading = false,
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                story = result.data ?: Story.Empty,
                                isLoading = false,
                                isConnectionProblem = result.message == ErrorMessage.IO_EXCEPTION.message,
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                story = result.data ?: Story.Empty,
                                isLoading = true,
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
