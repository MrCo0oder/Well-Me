package com.codebook.wellme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codebook.wellme.ui.screens.signup.SignInViewModel
import com.codebook.wellme.ui.theme.WellMeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        actionBar?.hide()
        installSplashScreen()
        Thread.sleep(1500)
        super.onCreate(savedInstanceState)
        setContent {
            WellMeTheme {
                AppNavigationGraph()
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WellMeTheme {
//        Greeting("Android")
//    }
//}