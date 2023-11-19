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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.codebook.wellme.R
import com.codebook.wellme.model.login.LoginStateUiEvents
import com.codebook.wellme.ui.ClickableText
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SocialMediaButton
import com.codebook.wellme.ui.SubHeadingText
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.utils.Screen
import com.codebook.wellme.utils.validateEmail
import com.codebook.wellme.utils.validatePassword

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: LoginViewModel = viewModel()
    LoginScreenContent(navController, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreenContent(navController: NavController, viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsState().value
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
            HeadlineLarge(stringResource(R.string.welcome_back))
            TextInputWithLabel(
                label = stringResource(R.string.e_mail),
                labelColor = Color.DarkGray,
                placeholder = stringResource(R.string.enter_your_e_mail_here),
                default = uiState.email,
                error = uiState.email.validateEmail(),
                leadingIcon = R.drawable.email, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            ) {
                viewModel.onEvent(LoginStateUiEvents.Email(it))
            }
            TextInputWithLabel(
                label = stringResource(R.string.password),
                labelColor = Color.DarkGray,
                placeholder = stringResource(R.string.place_the_password_here),
                default = uiState.password,
                error = uiState.password.validatePassword(),
                isPassword = true,
                leadingIcon = R.drawable.lock, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                )
            ) {
                viewModel.onEvent(LoginStateUiEvents.Password(it))
            }
            ClickableText(label = stringResource(R.string.forgot_your_password)) {
                navController.navigate(Screen.ForgetPasswordScreen.destination)
            }
            Spacer(modifier = Modifier.weight(1f))
            RectanglePrimaryButton(
                label = stringResource(R.string.login),
                isEnabled = viewModel.isScreenValid()
            ) {
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
                SubHeadingText(text = stringResource(R.string.or), color = DeepBlue)
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
                SubHeadingText(
                    text = stringResource(R.string.don_t_have_an_account_yet),
                    color = DeepBlue
                )
                ClickableText(label = stringResource(R.string.sign_up)) {
                    navController.navigate(Screen.SignUpScreen.destination) {
                        popUpTo(Screen.LoginScreen.destination) { inclusive = true }
                    }
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
//    LoginScreenContent(navController = rememberNavController(), viewModel = viewModel())
}