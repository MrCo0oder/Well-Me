package com.codebook.wellme.navigation

import com.codebook.wellme.navigation.Routes.ACTIVITY_SCREEN
import com.codebook.wellme.navigation.Routes.ACTIVITY_TIMER_SCREEN
import com.codebook.wellme.navigation.Routes.ADDS_SUPPLEMENT_SCREEN
import com.codebook.wellme.navigation.Routes.ADD_ACTIVITY_SCREEN
import com.codebook.wellme.navigation.Routes.EMAIL_SENT_SCREEN
import com.codebook.wellme.navigation.Routes.FORGET_PASSWORD_SCREEN
import com.codebook.wellme.navigation.Routes.HOME_SCREEN
import com.codebook.wellme.navigation.Routes.LOGIN_SCREEN
import com.codebook.wellme.navigation.Routes.MAIN_HOME_SCREEN
import com.codebook.wellme.navigation.Routes.ONBOARDING_SCREEN
import com.codebook.wellme.navigation.Routes.SETTINGS_SCREEN
import com.codebook.wellme.navigation.Routes.SIGNUP_SCREEN
import com.codebook.wellme.navigation.Routes.STARTING_ACTIVITY_SCREEN
import com.codebook.wellme.navigation.Routes.WELCOME_SCREEN

private object Routes {
    const val WELCOME_SCREEN = "WELCOME_SCREEN"
    const val ONBOARDING_SCREEN = "ONBOARDING_SCREEN"
    const val SIGNUP_SCREEN = "SIGNUP_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val FORGET_PASSWORD_SCREEN = "FORGET_PASSWORD_SCREEN"
    const val EMAIL_SENT_SCREEN = "EMAIL_SENT_SCREEN"
    const val MAIN_HOME_SCREEN = "MAIN_HOME_SCREEN"

    const val HOME_SCREEN = "HOME_SCREEN"
    const val ACTIVITY_SCREEN = "ACTIVITY_SCREEN"
    const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
    const val ADDS_SUPPLEMENT_SCREEN = "ADD_SUPPLEMENT_SCREEN"
    const val ADD_ACTIVITY_SCREEN = "ADD_ACTIVITY_SCREEN"
    const val STARTING_ACTIVITY_SCREEN = "STARTING_ACTIVITY_SCREEN"
    const val ACTIVITY_TIMER_SCREEN = "ACTIVITY_TIMER_SCREEN"
}

sealed class Screen(val destination: String) {
    object WelcomeScreen : Screen(WELCOME_SCREEN)
    object OnBoardingScreen : Screen(ONBOARDING_SCREEN)
    object SignUpScreen : Screen(SIGNUP_SCREEN)
    object LoginScreen : Screen(LOGIN_SCREEN)
    object ForgetPasswordScreen : Screen(FORGET_PASSWORD_SCREEN)
    object EmailSentScreen : Screen(EMAIL_SENT_SCREEN)
    object HomeScreen : Screen(HOME_SCREEN)
    object MainHomeScreen : Screen(MAIN_HOME_SCREEN)
    object ActivityScreen : Screen(ACTIVITY_SCREEN)
    object SettingsScreen : Screen(SETTINGS_SCREEN)
    object AddSupplementScreen : Screen(ADDS_SUPPLEMENT_SCREEN)
    object AddActivityScreen : Screen(ADD_ACTIVITY_SCREEN)
    object StartingActivityScreen : Screen(STARTING_ACTIVITY_SCREEN)
    object ActivityTimerScreen : Screen(ACTIVITY_TIMER_SCREEN)

}