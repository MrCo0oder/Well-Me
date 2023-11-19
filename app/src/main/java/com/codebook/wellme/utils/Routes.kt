package com.codebook.wellme.utils

import com.codebook.wellme.utils.Routes.EMAIL_SENT_SCREEN
import com.codebook.wellme.utils.Routes.FORGET_PASSWORD_SCREEN
import com.codebook.wellme.utils.Routes.LOGIN_SCREEN
import com.codebook.wellme.utils.Routes.ONBOARDING_SCREEN
import com.codebook.wellme.utils.Routes.SIGNUP_SCREEN
import com.codebook.wellme.utils.Routes.WELCOME_SCREEN

private object Routes {
    const val WELCOME_SCREEN = "WELCOME_SCREEN"
    const val ONBOARDING_SCREEN = "ONBOARDING_SCREEN"
    const val SIGNUP_SCREEN = "SIGNUP_SCREEN"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val FORGET_PASSWORD_SCREEN = "FORGET_PASSWORD_SCREEN"
    const val EMAIL_SENT_SCREEN = "EMAIL_SENT_SCREEN"
}

sealed class Screen(val destination: String) {
    object WelcomeScreen : Screen(WELCOME_SCREEN)
    object OnBoardingScreen : Screen(ONBOARDING_SCREEN)
    object SignUpScreen : Screen(SIGNUP_SCREEN)
    object LoginScreen : Screen(LOGIN_SCREEN)
    object ForgetPasswordScreen : Screen(FORGET_PASSWORD_SCREEN)
    object EmailSentScreen : Screen(EMAIL_SENT_SCREEN)

}