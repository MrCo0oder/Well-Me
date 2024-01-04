package com.codebook.wellme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codebook.wellme.ui.screens.home.activity.ActivityScreen
import com.codebook.wellme.ui.screens.home.homeScreen.HomeScreen
import com.codebook.wellme.ui.screens.home.settings.SettingsScreen

@Composable
fun HomeNavigationGraph(navController :NavHostController,mainNavController :NavHostController) {

    NavHost(navController = navController, startDestination = Screen.ActivityScreen.destination) {
        composable(Screen.HomeScreen.destination) {
            HomeScreen(navController,mainNavController)
        }
        composable(Screen.ActivityScreen.destination) {
            ActivityScreen(navController,mainNavController)
        }
        composable(Screen.SettingsScreen.destination) {
            SettingsScreen(navController,mainNavController)
        }
    }
}





