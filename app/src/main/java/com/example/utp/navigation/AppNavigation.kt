package com.example.utp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.utp.state.TaskState
import com.example.utp.ui.screen.DetailScreen
import com.example.utp.ui.screen.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onTaskClick = { taskId ->
                    navController.navigate("detail/$taskId")
                }
            )
        }
        composable(
            route = "detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            DetailScreen(
                taskId = taskId,
                taskState = TaskState,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
