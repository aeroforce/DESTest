package com.example.destest.core.main

sealed class UIEvent {
    data class ShowToast(val message: String) : UIEvent()
}