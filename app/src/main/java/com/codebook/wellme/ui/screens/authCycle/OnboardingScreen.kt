package com.codebook.wellme.ui.screens.authCycle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.ui.HorizontalPagerScreen
import com.codebook.wellme.ui.OutlinedButtonPurple
import com.codebook.wellme.ui.theme.VioletLight
import com.codebook.wellme.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController
) {
    Scaffold(
        containerColor = VioletLight,
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                HorizontalPagerScreen(Modifier.weight(1f).fillMaxWidth()) {
                    navigateToSignUpScreen(navController)
                }
            }
            OutlinedButtonPurple(Modifier.padding(vertical = 8.dp, horizontal = 16.dp), buttonModifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp),text = "Skip Intro") { navigateToSignUpScreen(navController) }
        }
    }
}

private fun navigateToSignUpScreen(navController: NavHostController) {
    navController.navigate(Screen.SignUpScreen.destination) {
        popUpTo(Screen.OnBoardingScreen.destination) { inclusive = true }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}