package com.example.destest.feature.content.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destest.core.ErrorMessage
import com.example.destest.core.service.appstate.AppStateService
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.domain.model.ContentItem
import com.example.destest.feature.content.domain.usecase.GetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val getContent: GetContent,
    private val appStateService: AppStateService,
) : ViewModel() {

    var content: List<ContentItem> by mutableStateOf(emptyList())
        private set

    init {
        fetchContent()
    }

    private fun fetchContent() {
        viewModelScope.launch {
            getContent.invoke()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            content = result.data ?: emptyList()
                            appStateService.setBusy(false)
                        }
                        is Resource.Error -> {
                            content = result.data ?: emptyList()
                            appStateService.setBusy(false)
                            result.message?.let { message ->
                                if (message == ErrorMessage.IO_EXCEPTION.message) {
                                    appStateService.setConnectionProblem(true)
                                }
                            }
                        }
                        is Resource.Loading -> {
                            content = result.data ?: emptyList()
                            appStateService.setBusy(true)
                        }
                    }
                }.launchIn(this)
        }
    }
}
