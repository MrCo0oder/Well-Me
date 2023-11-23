package com.codebook.wellme.ui.screens.authCycle.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.codebook.wellme.model.signup.CreateAccountState
import com.codebook.wellme.model.signup.CreateAccountStateUiEvents
import com.codebook.wellme.utils.Constants.PASSWORD_PATTERN
import com.codebook.wellme.utils.validateWithRegex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern

class CreateAccountViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<CreateAccountState> =
        MutableStateFlow(CreateAccountState())
    var uiState: StateFlow<CreateAccountState> = _uiState

    fun onEvent(events: CreateAccountStateUiEvents) {
        when (events) {
            is CreateAccountStateUiEvents.Email -> {
//                _uiState.value = uiState.value.copy()
                _uiState.update {
                    it.copy(email = events.value)
                }
            }

            is CreateAccountStateUiEvents.Password -> {
                _uiState.value = uiState.value.copy(password = events.value)
            }

            is CreateAccountStateUiEvents.Name -> {
                _uiState.value = uiState.value.copy(name = events.value)
            }
        }
    }


    private fun isValidEmail() = _uiState.value.email.isNotEmpty() && Pattern.matches(
        Patterns.EMAIL_ADDRESS.toString(),
        _uiState.value.email
    )

    fun isScreenValid(): Boolean =
        isValidEmail() && _uiState.value.password.validateWithRegex(
            PASSWORD_PATTERN
        ) && isValidName(_uiState.value.name)

    private fun isValidName(name: String): Boolean =
        name.trim().isNotEmpty() && name.trim().length >= 4

    fun validateName(name: String): String? {
        return if (name.trim().isEmpty()) {
            null
        } else if (name.trim().length < 4) {
            ("Please enter a valid name at least 4 characters")
        } else {
            null
        }
    }

}