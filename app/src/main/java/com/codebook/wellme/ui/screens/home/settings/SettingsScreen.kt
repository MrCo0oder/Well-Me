package com.codebook.wellme.ui.screens.home.settings

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun SettingsScreen(navController: NavHostController, mainNavController: NavHostController) {
    Surface {
        Text(text = "SettingsScreen")
    }
}
