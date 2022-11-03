package com.example.destest.feature.story.presentation

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
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.story.domain.usecase.GetStory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val getStory: GetStory,
    private val appStateService: AppStateService,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var story by mutableStateOf(Story.Empty)
        private set

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
                            story = result.data ?: Story.Empty
                            appStateService.setBusy(false)
                        }
                        is Resource.Error -> {
                            story = result.data ?: Story.Empty
                            result.message?.let { message ->
                                if (message == ErrorMessage.IO_EXCEPTION.message) {
                                    appStateService.setConnectionProblem(true)
                                }
                            }
                            appStateService.setBusy(false)
                        }
                        is Resource.Loading -> {
                            story = result.data ?: Story.Empty
                            appStateService.setBusy(true)
                        }
                    }
                }.launchIn(this)
        }
    }
}
