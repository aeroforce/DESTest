package com.example.destest.core.main.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.destest.core.service.appstate.AppStateService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appStateService: AppStateService,
) : ViewModel() {

    var busyState by mutableStateOf(false)
        private set

    var connectionProblemState by mutableStateOf(false)
        private set

    init {
        appStateService.isBusy()
            .onEach {
                busyState = it
            }
            .launchIn(viewModelScope)
        appStateService.isConnectionProblem()
            .onEach {
                connectionProblemState = it
            }.launchIn(viewModelScope)
    }
}
