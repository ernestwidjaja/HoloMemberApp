package com.example.jetsubmissionapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About: Screen("about")
    object Favorite : Screen("favorite")
    object DetailMember : Screen("home/{memberId}") {
        fun createRoute(memberId: String) = "home/$memberId"
    }
}