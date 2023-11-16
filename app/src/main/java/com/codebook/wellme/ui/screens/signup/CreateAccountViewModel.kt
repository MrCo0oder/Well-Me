package com.codebook.wellme.ui.screens.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.codebook.wellme.model.CreateAccountState
import com.codebook.wellme.model.CreateAccountStateUiEvents
import com.codebook.wellme.utils.Constants.PASSWORD_PATTERN
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.regex.Pattern

class CreateAccountViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<CreateAccountState> =
        MutableStateFlow(CreateAccountState())
    var uiState: StateFlow<CreateAccountState> = _uiState

    fun onEvent(events: CreateAccountStateUiEvents) {
        when (events) {
            is CreateAccountStateUiEvents.Email -> {
                _uiState.value = uiState.value.copy(email = events.value)
            }

            is CreateAccountStateUiEvents.Password -> {
                _uiState.value = uiState.value.copy(password = events.value)
            }

            is CreateAccountStateUiEvents.Name -> {
                _uiState.value = uiState.value.copy(name = events.value)
            }
        }
    }

    fun validateEmail(): String? {
        return if (_uiState.value.email.isEmpty()) {
            null
        } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), _uiState.value.email)) {
            "Please enter a valid email."
        } else {
            null
        }
    }

    private fun isValidEmail() = _uiState.value.email.isNotEmpty() && Pattern.matches(
        Patterns.EMAIL_ADDRESS.toString(),
        _uiState.value.email
    )

    fun isScreenValid(): Boolean =
        isValidEmail() && validateWithRegex(
            _uiState.value.password,
            PASSWORD_PATTERN
        ) && isValidName(_uiState.value.name)

    private fun isValidName(name: String): Boolean = name.isNotEmpty() && name.length >= 4

    fun validateName(name: String): String? {
        return if (name.isEmpty()) {
            null
        } else if (name.length <= 4) {
            ("Please enter a valid name at least 4 characters.")
        } else {
            null
        }
    }

    fun validatePassword(): String? {
        return if (_uiState.value.password.isEmpty()) {
            null
        } else if (!validateWithRegex(_uiState.value.password, PASSWORD_PATTERN)) {
            (" ")
        } else {
            null
        }
    }

    fun validateWithRegex(string: String, regex: String): Boolean {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(string)
        return matcher.matches()
    }


}