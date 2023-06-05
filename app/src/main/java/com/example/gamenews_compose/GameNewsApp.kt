package com.example.gamenews_compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.gamenews_compose.ui.theme.GameNews_ComposeTheme
import com.example.gamenews_compose.ui.theme.navigation.NavigationItem
import com.example.gamenews_compose.ui.theme.navigation.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.gamenews_compose.ui.theme.screen.detail.DetailScreen
import com.example.gamenews_compose.ui.theme.screen.home.HomeScreen
import com.example.gamenews_compose.ui.theme.screen.profile.ProfileScreen

@Composable
fun GameNewsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if(currentRoute != Screen.DetailGames.route){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { gamesId ->
                    navController.navigate(Screen.DetailGames.createRoute(gamesId)) }
                )
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
            composable(
                route = Screen.DetailGames.route,
                arguments = listOf(navArgument("gamesid"){type = NavType.LongType}),
            ){
                val id = it.arguments?.getLong("gamesid")?: -1L
                DetailScreen(gamesId = id, navigateBack = { navController.navigateUp() })
            }

        }
    }

}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = androidx.compose.ui.res.stringResource(id = R.string.menu_home),
                ikon = Icons.Default.Home,
                screen = Screen.Home,

                ),
            NavigationItem(
                title = androidx.compose.ui.res.stringResource(id = R.string.menu_profile),
                ikon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = item.ikon, contentDescription = item.title)
                    },
                    label = { Text(text = (item.title)) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun GamesNewsAppPreview() {
    GameNews_ComposeTheme {
        GameNewsApp()
    }
}
