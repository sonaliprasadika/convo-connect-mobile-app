package com.example.mobilecomputing

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Chat : Screen(route = "chat_screen")

}