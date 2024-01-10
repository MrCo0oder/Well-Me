package com.codebook.wellme.navigation

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebook.wellme.service.StopwatchService
import com.codebook.wellme.service.StopwatchState
import com.codebook.wellme.ui.screens.authCycle.forgetPassword.EmailSentScreen
import com.codebook.wellme.ui.screens.authCycle.forgetPassword.ForgetPasswordScreen
import com.codebook.wellme.ui.screens.authCycle.login.LoginScreen
import com.codebook.wellme.ui.screens.authCycle.OnboardingScreen
import com.codebook.wellme.ui.screens.authCycle.WelcomeScreen
import com.codebook.wellme.ui.screens.authCycle.signup.SignUpScreen
import com.codebook.wellme.ui.screens.home.MainHomeScreen
import com.codebook.wellme.ui.screens.home.activity.activityTimer.ActivityTimerScreen
import com.codebook.wellme.ui.screens.home.activity.startingActivity.StartingActivityScreen
import com.codebook.wellme.ui.screens.home.homeScreen.addActivity.AddActivityScreen
import com.codebook.wellme.ui.screens.home.homeScreen.addSupplement.AddSupplementScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigationGraph(stopwatchService: StopwatchService) {
    val navController = rememberNavController()
//    val createAccountViewModel = CreateAccountViewModel()
//    val mSignInViewModel: SignInGoogleViewModel = viewModel(
//        factory = SignInGoogleViewModelFactory(LocalContext.current.applicationContext as Application)
//    )
    NavHost(
        navController = navController,
        startDestination = if (stopwatchService.currentState.value != StopwatchState.Idle || stopwatchService.currentState.value == StopwatchState.Started)
            Screen.ActivityTimerScreen.destination else Screen.MainHomeScreen.destination
    ) {
        composable(Screen.WelcomeScreen.destination) {
            WelcomeScreen(navController)
        }
        composable(Screen.OnBoardingScreen.destination) {
            OnboardingScreen(navController)
        }
        composable(Screen.SignUpScreen.destination) {
            SignUpScreen(navController)
        }
        composable(Screen.LoginScreen.destination) {
            LoginScreen(navController)
        }
        composable(Screen.ForgetPasswordScreen.destination) {
            ForgetPasswordScreen(navController)
        }
        composable(Screen.EmailSentScreen.destination) {
            EmailSentScreen(navController)
        }
        composable(Screen.MainHomeScreen.destination) {
            MainHomeScreen(navController)
        }
        composable(Screen.AddSupplementScreen.destination) {
            AddSupplementScreen(navController)
        }
        composable(Screen.ActivityScreen.destination) {
            AddActivityScreen(navController)
        }
        composable(Screen.StartingActivityScreen.destination) {
            StartingActivityScreen(navController)
        }
        composable(Screen.ActivityTimerScreen.destination) {

            ActivityTimerScreen(navController, stopwatchService)
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
           }
           */
    }
}

fun Context.isMyServiceRunning(serviceClass: Class<out Service>) = try {
    (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
        .getRunningServices(Int.MAX_VALUE)
        .any { it.service.className == serviceClass.name }
} catch (e: Exception) {
    false
}