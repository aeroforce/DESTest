package com.example.destest.core.service.appstate

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppStateServiceImpl: AppStateService {

    private val isBusy = MutableStateFlow(false)
    private val isConnectionProblem = MutableStateFlow(false)

    override fun isBusy(): Flow<Boolean> = isBusy.asStateFlow()

    override fun setBusy(isBusy: Boolean) {
        this.isBusy.value = isBusy
    }

    override fun isConnectionProblem(): Flow<Boolean> = isConnectionProblem.asStateFlow()

    override fun setConnectionProblem(isProblem: Boolean) {
        this.isConnectionProblem.value = isProblem
    }
}