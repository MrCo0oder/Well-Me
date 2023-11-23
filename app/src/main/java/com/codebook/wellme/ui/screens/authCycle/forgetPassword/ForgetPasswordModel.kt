package com.codebook.wellme.ui.screens.authCycle.forgetPassword

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.codebook.wellme.model.forgetPassword.ForgetPasswordState
import com.codebook.wellme.model.forgetPassword.ForgetPasswordStateUiEvents
import com.codebook.wellme.utils.validateEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class ForgetPasswordModel : ViewModel() {
    private var _uiState: MutableStateFlow<ForgetPasswordState> =
        MutableStateFlow(ForgetPasswordState())
    var uiState: StateFlow<ForgetPasswordState> = _uiState

    fun onEvent(events: ForgetPasswordStateUiEvents) {
        when (events) {
            is ForgetPasswordStateUiEvents.Email -> {
                _uiState.value = uiState.value.copy(email = events.value)
            }

        }
    }
    fun isScreenValid(): Boolean = _uiState.value.email.isNotEmpty() && Pattern.matches(
        Patterns.EMAIL_ADDRESS.toString(),
        _uiState.value.email
    )

}