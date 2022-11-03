package com.example.destest.core.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.example.destest.core.kotlin.pass

open class LifecycleViewModel : ViewModel(), LifecycleObserver {

    private var firstCreate = true

    @CallSuper
    open fun onFirstCreate() {
        firstCreate = false
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
        if (firstCreate) {
            onFirstCreate()
        }
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() = pass

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() = pass

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() = pass

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() = pass
}