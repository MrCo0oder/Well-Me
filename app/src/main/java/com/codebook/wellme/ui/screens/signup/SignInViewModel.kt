package com.codebook.wellme.ui.screens.signup

import androidx.lifecycle.ViewModel
import com.codebook.wellme.model.signupWithGoogle.SignInResult
import com.codebook.wellme.model.signupWithGoogle.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel:ViewModel() {
    private var _userState = MutableStateFlow(SignInState())
    val userState = _userState.asStateFlow()


    fun onSignInResult(result: SignInResult) {
        _userState.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }
    fun resetState(){
        _userState.update { SignInState() }
    }
}