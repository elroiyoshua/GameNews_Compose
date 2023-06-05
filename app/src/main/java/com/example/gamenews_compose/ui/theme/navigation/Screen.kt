package com.example.gamenews_compose.ui.theme.navigation

sealed class Screen (val route : String){
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailGames : Screen("home/{gamesid}"){
        fun createRoute(gamesid : Long) = "home/$gamesid"
    }
}