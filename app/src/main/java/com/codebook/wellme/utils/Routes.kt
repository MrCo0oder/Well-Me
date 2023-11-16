package com.codebook.wellme.utils

import com.codebook.wellme.utils.Routes.LOGIN_SCREEN
import com.codebook.wellme.utils.Routes.ONBOARDING_SCREEN
import com.codebook.wellme.utils.Routes.SIGNUP_SCREEN
import com.codebook.wellme.utils.Routes.WELCOME_SCREEN

private object Routes {
    const val WELCOME_SCREEN = "WELCOME_SCREEN"
    const val ONBOARDING_SCREEN = "ONBOARDING_SCREEN"
    const val SIGNUP_SCREEN = "SIGNUP_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
//    const val BASIC_INFO_SCREEN = "BASIC_INFO_SCREEN"
//    const val GENDER_SCREEN = "GENDER_SCREEN"
//    const val HABITS_SCREEN = "HABITS_SCREEN"
}

sealed class Screen(val destination: String) {
    object WelcomeScreen : Screen(WELCOME_SCREEN)
    object OnBoardingScreen : Screen(ONBOARDING_SCREEN)
    object SignUpScreen : Screen(SIGNUP_SCREEN)
    object LoginScreen : Screen(LOGIN_SCREEN)

//    object BasicInfoScreen : Screen(BASIC_INFO_SCREEN)
//    object GenderScreen : Screen(GENDER_SCREEN)
//    object HabitsScreen : Screen(HABITS_SCREEN)
}