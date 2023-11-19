package com.codebook.wellme.model.signupWithGoogle

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)