package com.codebook.wellme.ui.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.ui.ClickableText
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SocialMediaButton
import com.codebook.wellme.ui.SubHeadingText
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.utils.Screen

@Composable
fun LoginScreen(navController: NavHostController) {
    LoginScreenContent(navController)
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreenContent(navController: NavController) {
    Scaffold(
        Modifier
            .background(Color.White)
            .padding(start = 24.dp, end = 24.dp , top = 30.dp),
        containerColor = Color.White,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.3f))
            HeadlineLarge("Welcome back")
            TextInputWithLabel(
                label = "E-mail",
                labelColor = Color.DarkGray,
                placeholder = "Enter your e-mail here",
                default = "",
                error = null,
                leadingIcon = R.drawable.email
            ) {}
            TextInputWithLabel(
                label = "Password",
                labelColor = Color.DarkGray,
                placeholder = "Place the password here",
                default = "",
                error = null,
                isPassword = true,
                leadingIcon = R.drawable.lock
            ) {}
            ClickableText(label = "Forgot your password?") {

            }
            Spacer(modifier = Modifier.weight(0.5f))
            RectanglePrimaryButton(label = "Log in") {
//                navController.navigate(Screen.OnBoardingScreen.destination)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .height(1.dp)
                        .weight(1f)
                )
                SubHeadingText(text = "Or", color = DeepBlue)
                Box(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .height(1.dp)
                        .weight(1f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                SocialMediaButton(icon = R.drawable.google) {

                }
                SocialMediaButton(icon = R.drawable.facebook) {

                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SubHeadingText(text = "Don’t have an account yet?", color = DeepBlue)
                ClickableText(label = "Sign up") {
                    navController.navigate(Screen.SignUpScreen.destination){
                        popUpTo(Screen.LoginScreen.destination) { inclusive = true }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.3f))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
    LoginScreenContent(navController = rememberNavController())
}