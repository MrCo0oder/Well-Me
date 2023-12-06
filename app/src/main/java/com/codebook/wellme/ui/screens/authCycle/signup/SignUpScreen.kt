@file:OptIn(ExperimentalMaterial3Api::class)

package com.codebook.wellme.ui.screens.authCycle.signup

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.model.signup.CreateAccountStateUiEvents
import com.codebook.wellme.navigation.Screen
import com.codebook.wellme.ui.BodyText3Text
import com.codebook.wellme.ui.ClickableText
import com.codebook.wellme.ui.CustomCheckbox
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.PasswordValidationComponent
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SocialMediaButton
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.utils.Constants
import com.codebook.wellme.utils.Constants.AT_LEAST_NUM_OR_CHAR_PATTERN
import com.codebook.wellme.utils.Constants.UPPER_LOWER_PATTERN
import com.codebook.wellme.utils.GoogleAuthUiClient
import com.codebook.wellme.utils.validateEmail
import com.codebook.wellme.utils.validateWithRegex
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: CreateAccountViewModel = viewModel()
    val signInViewModel: SignInViewModel = viewModel()
    SignUpScreenContent(navController, viewModel, signInViewModel)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpScreenContent(
    navController: NavController,
    viewModel: CreateAccountViewModel,
    signViewModel: SignInViewModel,
) {
    val uiState = viewModel.uiState.collectAsState().value
    val googleState by signViewModel.userState.collectAsState()
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    val googleAuthClient by lazy {
        GoogleAuthUiClient(
            context,
            Identity.getSignInClient(context)
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                scope.launch {
                    val signInResult = googleAuthClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    signViewModel.onSignInResult(signInResult)
                }
            }
        }
    )
    Scaffold(
        Modifier
            .background(White)
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
        containerColor = White,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            HeadlineLarge(stringResource(R.string.create_an_account))
            TextInputWithLabel(
                label = stringResource(R.string.full_name),
                labelColor = DarkGray,
                placeholder = stringResource(R.string.enter_your_name),
                default = uiState.name,
                error = viewModel.validateName(uiState.name),
                leadingIcon = R.drawable.profile, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            ) {
                viewModel.onEvent(CreateAccountStateUiEvents.Name(it))
            }
            TextInputWithLabel(
                label = stringResource(R.string.e_mail),
                labelColor = DarkGray,
                placeholder = stringResource(R.string.enter_your_e_mail_here),
                default = uiState.email,
                error = uiState.email.validateEmail(),
                leadingIcon = R.drawable.email, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            ) {
                viewModel.onEvent(CreateAccountStateUiEvents.Email(it))
            }
            TextInputWithLabel(
                label = stringResource(R.string.password),
                labelColor = DarkGray,
                placeholder = stringResource(R.string.place_the_password_here),
                default = uiState.password,
                error = if (uiState.password.validateWithRegex(Constants.PASSWORD_PATTERN) || uiState.password.isNullOrEmpty()) null else "  ",
                isPassword = true,
                leadingIcon = R.drawable.lock, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                )
            ) { password ->
                viewModel.onEvent(CreateAccountStateUiEvents.Password(password))
            }
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PasswordValidationComponent(
                    label = stringResource(R.string.at_least_8_characters),
                    isValid = uiState.password.length >= 8
                )
                PasswordValidationComponent(
                    label = stringResource(R.string.both_uppercase_and_lowercase_characters),
                    isValid = uiState.password.validateWithRegex(UPPER_LOWER_PATTERN)
                )
                PasswordValidationComponent(
                    stringResource(R.string.at_least_one_number_or_symbol),
                    uiState.password.validateWithRegex(AT_LEAST_NUM_OR_CHAR_PATTERN)
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
                BodyText3Text(
                    text = stringResource(R.string.by_continuing_you_accept_our_privacy_policy_and_term_of_use),
                    color = DarkGray,
                    TextAlign.Justify
                )
            }
            RectanglePrimaryButton(
                label = stringResource(R.string.sign_up),
                isEnabled = viewModel.isScreenValid() && checkBox.value
            ) {
                navController.navigate(Screen.MainHomeScreen.destination) {
                    popUpTo(Screen.SignUpScreen.destination) { inclusive = true }
                }
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
                BodyText3Text(text = stringResource(R.string.or), color = DeepBlue)
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
                    scope.launch {
                        val signInIntentSender = googleAuthClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
                SocialMediaButton(icon = R.drawable.facebook) {

                }
            }
            LaunchedEffect(key1 = googleState.isSignInSuccessful) {
                if (googleState.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        googleAuthClient.getSignedInUser().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i("User Info :", googleAuthClient.getSignedInUser().toString())
//                    signViewModel.resetState()
                    navController.navigate(Screen.MainHomeScreen.destination) {
                        popUpTo(Screen.SignUpScreen.destination) { inclusive = true }
                    }
                }
            }
            LaunchedEffect(key1 = googleState.signInError) {
                if (!googleState.signInError.isNullOrEmpty())
                    Toast.makeText(
                        context,
                        googleState.signInError.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BodyText3Text(
                    text = stringResource(R.string.already_have_an_account),
                    color = DeepBlue
                )
                ClickableText(label = stringResource(R.string.login)) {
                    navController.navigate(Screen.LoginScreen.destination) {
                        popUpTo(Screen.SignUpScreen.destination) { inclusive = true }
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
    SignUpScreenContent(
        navController = rememberNavController(), viewModel(), viewModel()
    )
}