package com.codebook.wellme.ui.screens.forgetPassword

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.R
import com.codebook.wellme.model.forgetPassword.ForgetPasswordStateUiEvents
import com.codebook.wellme.ui.BodyTextTwo
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.theme.DarkGrey
import com.codebook.wellme.utils.Screen
import com.codebook.wellme.utils.validateEmail

@Composable
fun ForgetPasswordScreen(navController: NavHostController) {
    val viewModel: ForgetPasswordModel = viewModel()
    ForgetPasswordScreenContent(navController, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ForgetPasswordScreenContent(
    navController: NavController,
    viewModel: ForgetPasswordModel
) {
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
            HeadlineLarge(stringResource(R.string.enter_your_e_mail))
            BodyTextTwo(
                text = stringResource(R.string.you_will_receive_a_link_to_confirm_the_password_change_to_the_e_mail_address_provided),
                color = DarkGrey,
                modifier = Modifier.fillMaxWidth()
            )
            TextInputWithLabel(
                label = stringResource(R.string.e_mail),
                labelColor = DarkGrey,
                placeholder = stringResource(R.string.enter_your_e_mail_here),
                default = uiState.email,
                error = uiState.email.validateEmail(),
                leadingIcon = R.drawable.email, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            ) {
                viewModel.onEvent(ForgetPasswordStateUiEvents.Email(it))
            }

            Spacer(modifier = Modifier.weight(1f))
            RectanglePrimaryButton(
                label = stringResource(R.string.confirm_e_mail),
                isEnabled = viewModel.isScreenValid()
            ) {
                navController.navigate(Screen.EmailSentScreen.destination) {
                    popUpTo(Screen.ForgetPasswordScreen.destination) { inclusive = true }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFun() {
    ForgetPasswordScreenContent(navController = rememberNavController(), viewModel = viewModel())
}