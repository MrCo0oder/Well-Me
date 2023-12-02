package com.codebook.wellme.ui.screens.authCycle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.BodyText3Text
import com.codebook.wellme.navigation.Screen

@Composable
fun WelcomeScreen(
    navController: NavHostController,
) {
    WelcomeScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreenContent(
    navController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            RectanglePrimaryButton(Modifier.padding(horizontal = 24.dp), label = "Let’s start") {
                navController.navigate(Screen.OnBoardingScreen.destination) {
                    popUpTo(Screen.WelcomeScreen.destination) { inclusive = true }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(vertical = 60.dp, horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.brand_logo_big),
                contentDescription = null,
                Modifier
                    .size(104.dp), contentScale = ContentScale.FillBounds
            )
            HeadlineLarge("Welcome to\nWell Me")
            BodyText3Text("Just take a look and take action!")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreenContent(navController = rememberNavController())
}
