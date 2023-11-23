package com.codebook.wellme.ui.screens.authCycle.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.codebook.wellme.model.login.LoginState
import com.codebook.wellme.model.login.LoginStateUiEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState())
    var uiState: StateFlow<LoginState> = _uiState

    fun onEvent(events: LoginStateUiEvents) {
        when (events) {
            is LoginStateUiEvents.Email -> {
                _uiState.value = uiState.value.copy(email = events.value)
            }

            is LoginStateUiEvents.Password -> {
                _uiState.value = uiState.value.copy(password = events.value)
            }
        }
    }

    private fun isValidEmail() = _uiState.value.email.isNotEmpty() && Pattern.matches(
        Patterns.EMAIL_ADDRESS.toString(),
        _uiState.value.email
    )

    fun isScreenValid(): Boolean =
        isValidEmail() && _uiState.value.password.length >= 8 && !_uiState.value.password.isNullOrEmpty()
}