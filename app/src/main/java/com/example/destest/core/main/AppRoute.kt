package com.example.destest.core.main

enum class AppRoute(val route: String) {
    HOME("home"),
    STORY("story"),
    PLAYER("player"),
}

enum class AppRouteParameter(val param: String) {
    STORY("id"),
    PLAYER("id")
}
