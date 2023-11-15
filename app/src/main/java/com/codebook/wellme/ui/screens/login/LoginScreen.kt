@file:OptIn(ExperimentalMaterial3Api::class)

package com.codebook.wellme.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codebook.wellme.R
import com.codebook.wellme.ui.HeadlineLarge
import com.codebook.wellme.ui.RectanglePrimaryButton
import com.codebook.wellme.ui.SubHeadingText
import com.codebook.wellme.ui.TextInputWithLabel
import com.codebook.wellme.ui.theme.DeepBlue
import com.codebook.wellme.ui.theme.PurplePlum
import com.codebook.wellme.utils.Screen

@Composable
fun LoginScreen(navController: NavController) {
    LoginScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreenContent(navController: NavController) {
    Scaffold(
        Modifier
            .background(White)
            .padding(horizontal = 24.dp),
        containerColor = Color.White,
//        floatingActionButton = {
//            RectanglePrimaryButton(Modifier.padding(horizontal = 24.dp), label = "Sign Up") {
//                navController.navigate(Screen.OnBoardingScreen.destination)
//            }
//        },
//        floatingActionButtonPosition = FabPosition.Center
    ) { _ ->
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
                default = "",
                error = null,
                leadingIcon = R.drawable.profile
            ) {}
            TextInputWithLabel(
                label = "E-mail",
                labelColor = DarkGray,
                placeholder = "Enter your e-mail here",
                default = "",
                error = null,
                leadingIcon = R.drawable.email
            ) {}
            TextInputWithLabel(
                label = "Password",
                labelColor = DarkGray,
                placeholder = "Place the password here",
                default = "",
                error = null,
                isPassword = true,
                leadingIcon = R.drawable.lock
            ) {}
            Box(
                modifier = Modifier
                    .background(LightGray)
                    .height(1.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
            )
            val checkBox = remember {
                mutableStateOf(true)
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, DarkGray),
                            RoundedCornerShape(5.dp)
                        )
                        .padding(4.dp)
                        .size(16.dp),
                    checked = checkBox.value,
                    onCheckedChange = {
                        checkBox.value = it
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = PurplePlum,
                        checkedColor = Transparent,
                        uncheckedColor = Transparent
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                SubHeadingText(
                    text = "By continuing you accept our Privacy Policy and Term of Use.",
                    color = DarkGray
                )
            }
            RectanglePrimaryButton(label = "Sign Up") {
//                navController.navigate(Screen.OnBoardingScreen.destination)
            }
            SubHeadingText(text = "Or", color = DeepBlue)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.size(70.dp), border =
                    BorderStroke(1.dp, LightGray), shape = RoundedCornerShape(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null, Modifier.size(30.dp)
                    )
                }
                OutlinedButton(
                    onClick = { /*TODO*/ }, modifier = Modifier.size(70.dp), border =
                    BorderStroke(1.dp, LightGray), shape = RoundedCornerShape(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null, Modifier.size(30.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SubHeadingText(text = "Already have an account?", color = DeepBlue)
                TextButton(onClick = { /*TODO*/ }) {
                    SubHeadingText(text = "Login", color = PurplePlum)
                }
            }
            Spacer(modifier = Modifier.weight(0.005f))

        }
    }
}
