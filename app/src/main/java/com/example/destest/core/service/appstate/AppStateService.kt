package com.example.destest.core.service.appstate

import kotlinx.coroutines.flow.Flow

/**
 * Responsible for Application state functionality
 */

interface AppStateService {

    /**
     * @return busy state of app used for handling common loading overlay visibility
     */
    fun isBusy(): Flow<Boolean>

    /**
     * Sets busy state of app used for loading overlay visibility
     */
    fun setBusy(isBusy: Boolean)

    /**
     * @return connectivity problem state, used to show offline informational label
     */
    fun isConnectionProblem(): Flow<Boolean>

    /**
     * Sets connection problem state
     */
    fun setConnectionProblem(isProblem: Boolean)
}
