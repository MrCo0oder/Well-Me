@file:OptIn(ExperimentalMaterial3Api::class)

package com.codebook.wellme.ui.screens.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.model.CreateAccountStateUiEvents
import com.codebook.wellme.ui.ClickableText
import com.codebook.wellme.ui.CustomCheckbox
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SocialMediaButton
import com.codebook.wellme.ui.SubHeadingText
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.theme.Alert
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.Turquoise
import com.codebook.wellme.utils.Screen

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: CreateAccountViewModel = viewModel()
    SignUpScreenContent(navController, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreenContent(navController: NavController, viewModel: CreateAccountViewModel) {
    val uiState = viewModel.uiState.collectAsState().value
    Scaffold(
        Modifier
            .background(White)
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
        containerColor = White,
    ) {
        Column(
            Modifier
//                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.005f))
            HeadlineLarge("Create an account")
            TextInputWithLabel(
                label = "Full Name",
                labelColor = DarkGray,
                placeholder = "Enter your name",
                default = uiState.name,
                error = viewModel.validateName(uiState.name),
                leadingIcon = R.drawable.profile
            ) {
                viewModel.onEvent(CreateAccountStateUiEvents.Name(it))
            }
            TextInputWithLabel(
                label = "E-mail",
                labelColor = DarkGray,
                placeholder = "Enter your e-mail here",
                default = uiState.email,
                error = viewModel.validateEmail(),
                leadingIcon = R.drawable.email
            ) {
                viewModel.onEvent(CreateAccountStateUiEvents.Email(it))
            }
            TextInputWithLabel(
                label = "Password",
                labelColor = DarkGray,
                placeholder = "Place the password here",
                default = uiState.password,
                error = viewModel.validatePassword(),
                isPassword = true,
                leadingIcon = R.drawable.lock
            ) { password ->
                viewModel.onEvent(CreateAccountStateUiEvents.Password(password))
            }
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PasswordValidationComponent(
                    "At least 8 characters",
                    uiState.password.length >= 8
                )
                PasswordValidationComponent(
                    "Both uppercase and lowercase characters",
                    viewModel.validateWithRegex(
                        uiState.password,
                        "^(?=.*[a-z])(?=.*[A-Z]).+\$"
                    )
                )
                PasswordValidationComponent(
                    "At least one number & symbol",
                    viewModel.validateWithRegex(
                        uiState.password,
                        "^(?=.*[0-9])(?=.*[!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\\\/-]).+\$"
                    )
                )
            }
            Box(
                modifier = Modifier
                    .background(LightGray)
                    .height(1.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
            )
            val checkBox = remember {
                mutableStateOf(false)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {

                CustomCheckbox(checkBox) { }
                Spacer(modifier = Modifier.width(8.dp))
                SubHeadingText(
                    text = "By continuing you accept our Privacy Policy and Term of Use.",
                    color = DarkGray,
                    TextAlign.Justify
                )
            }
            RectanglePrimaryButton(
                label = "Sign Up",
                isEnabled = viewModel.isScreenValid() && checkBox.value
            ) {
//                navController.navigate(Screen.OnBoardingScreen.destination)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(LightGray)
                        .height(1.dp)
                        .weight(1f)
                )
                SubHeadingText(text = "Or", color = DeepBlue)
                Box(
                    modifier = Modifier
                        .background(LightGray)
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
                SubHeadingText(text = "Already have an account?", color = DeepBlue)
                ClickableText(label = "Login") {
                    navController.navigate(Screen.LoginScreen.destination) {
                        popUpTo(Screen.SignUpScreen.destination) { inclusive = true }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.005f))
        }
    }

}

@Composable
private fun PasswordValidationComponent(label: String, isValid: Boolean) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        if (isValid)
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Turquoise
            ) else
            Icon(
                painter = painterResource(id = R.drawable.no_data),
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Alert
            )
        Spacer(modifier = Modifier.width(8.dp))
        SubHeadingText(
            text = label,
            color = DarkGray,
            TextAlign.Justify
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
    SignUpScreenContent(navController = rememberNavController(), viewModel = viewModel())
}