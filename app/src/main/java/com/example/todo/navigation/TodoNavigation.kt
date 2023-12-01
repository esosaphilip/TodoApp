package com.example.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.detail.DetailScreen
import com.example.todo.home.HomeScreen

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
    ) {
        composable(NavRoute.Home.route) {
            HomeScreen {
                navController.navigate(NavRoute.Detail.route + "/${it?.id ?: -1}") {
                }
            }
        }
        composable(
            NavRoute.Detail.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType }),
        ) {
            DetailScreen(selectedId = it.arguments?.getLong("id") ?: -1) {
                navController.navigateUp()
            }
        }
    }
}
