package com.codebook.wellme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.ui.screens.OnboardingScreen
import com.codebook.wellme.ui.screens.WelcomeScreen
import com.codebook.wellme.ui.screens.login.LoginScreen
import com.codebook.wellme.utils.Screen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
//    val createAccountViewModel = CreateAccountViewModel()
//    val mSignInViewModel: SignInGoogleViewModel = viewModel(
//        factory = SignInGoogleViewModelFactory(LocalContext.current.applicationContext as Application)
//    )
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.destination) {
        composable(Screen.WelcomeScreen.destination) {
            WelcomeScreen(navController)
        }
        composable(Screen.OnBoardingScreen.destination) {
            OnboardingScreen(navController)
        }
        composable(Screen.LoginScreen.destination) {
            LoginScreen(navController)
        }
        /*   composable(Screen.OnBoardingScreen.destination) {
               OnboardingScreen(mSignInViewModel, navController) {
                   navController.navigate(buildString {
                       append(Screen.BasicInfoScreen.destination)
                       append("/")
                       append(it.first)
                       append("/")
                       append(it.second)
                   })
               }
           }
           composable(
               "${Screen.BasicInfoScreen.destination}/{${USER_NAME}}/{${USER_MAIL}}",
               arguments = listOf(
                   navArgument(name = USER_NAME) {
                       type = NavType.StringType
                   },
                   navArgument(name = USER_MAIL) {
                       type = NavType.StringType
                   }
               ),
           ) {
               it.arguments?.apply {
                   BasicInfoScreen(
                       viewModel = createAccountViewModel,
                       googleUserModel = GoogleUserModel(
                           getString(USER_NAME),
                           getString(USER_MAIL)
                       ), goBack = { navController.popBackStack() }
                   ) {
                       navController.navigate(Screen.GenderScreen.destination)
                   }
               }

           }
           composable(Screen.LoginScreen.destination) {
               LoginWithEmailScreen(navController) {
                   navController.navigate(buildString {
                       append(Screen.BasicInfoScreen.destination)
                       append("/")
                       append(it.first)
                       append("/")
                       append(it.second)
                   })
               }
           }
           composable(Screen.GenderScreen.destination) {
               GenderScreen(navController, createAccountViewModel) {
                   navController.navigate(Screen.HabitsScreen.destination)
               }
           }
           composable(Screen.HabitsScreen.destination) {
               HabitsScreen(navController, createAccountViewModel)
           }*/
    }
}

//@Composable
//fun ChangeStatusBarColor(color: Color) {
//    val view = LocalView.current
//    val darkTheme = isSystemInDarkTheme()
//    if (!view.isInEditMode) {
//        SideEffect {
//            val activity = view.context as Activity
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                activity.window.statusBarColor = color.toArgb()
//                WindowCompat.getInsetsController(
//                    activity.window,
//                    view
//                ).isAppearanceLightStatusBars = !darkTheme
//            }
//        }
//    }
//}