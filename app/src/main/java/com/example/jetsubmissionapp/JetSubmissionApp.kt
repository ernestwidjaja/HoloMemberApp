package com.example.jetsubmissionapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetsubmissionapp.ui.components.BottomBar
import com.example.jetsubmissionapp.ui.navigation.Screen
import com.example.jetsubmissionapp.ui.screen.about.AboutScreen
import com.example.jetsubmissionapp.ui.screen.detail.DetailScreen
import com.example.jetsubmissionapp.ui.screen.favorite.FavoriteScreen
import com.example.jetsubmissionapp.ui.screen.home.HomeScreen

@Composable
fun JetSubmissionApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMember.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { memberId ->
                        navController.navigate(Screen.DetailMember.createRoute(memberId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { memberId ->
                        navController.navigate(Screen.DetailMember.createRoute(memberId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailMember.route,
                arguments = listOf(navArgument("memberId") {
                    type = NavType.StringType
                }),
            ) {
                val id = it.arguments?.getString("memberId")
                DetailScreen(
                    memberId = id.toString(),
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}