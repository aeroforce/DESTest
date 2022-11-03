package com.example.destest.core.service.appstate

import kotlinx.coroutines.flow.Flow

interface AppStateService {

    fun isBusy(): Flow<Boolean>

    fun setBusy(isBusy: Boolean)

    fun isConnectionProblem(): Flow<Boolean>

    fun setConnectionProblem(isProblem: Boolean)
}
