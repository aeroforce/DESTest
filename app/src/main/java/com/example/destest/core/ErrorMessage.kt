package com.example.destest.core

enum class ErrorMessage(val message: String) {
    HTTP_EXCEPTION("Http error"),
    IO_EXCEPTION("Connection problem error"),
    UNKNOWN("Unknown error occured")
}