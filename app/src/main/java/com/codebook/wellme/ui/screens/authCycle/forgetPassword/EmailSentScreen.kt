package com.codebook.wellme.ui.screens.authCycle.forgetPassword

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.navigation.Screen

@Composable
fun EmailSentScreen(navController: NavHostController) {
    EmailSentScreenContent(navController)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailSentScreenContent(navController: NavController) {
    Scaffold(
        Modifier
            .background(Color.White)
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
        containerColor = Color.White,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(R.drawable.forget_password_illstration),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = stringResource(R.string.check_your_email_inbox_to_reset_your_password),
                style = MaterialTheme.typography.headlineMedium.copy(
                    DeepBlue
                ), textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))
            RectanglePrimaryButton(label = stringResource(R.string.login)) {
                navController.navigate(Screen.LoginScreen.destination) {
                    popUpTo(Screen.EmailSentScreen.destination) {
                        inclusive = true
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
    EmailSentScreenContent(navController = rememberNavController())
}