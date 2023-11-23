package com.codebook.wellme.ui.screens.home.progress

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProgressScreen(navController: NavHostController, mainNavController: NavHostController) {
    Surface {
        Text(text = "ProgressScreen")
    }
}