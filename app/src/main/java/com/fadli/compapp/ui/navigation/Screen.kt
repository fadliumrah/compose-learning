package com.fadli.compapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailHero : Screen("home/{rewardId}") {
        fun createRoute(heroId: Long) = "home/$heroId"
    }
}
