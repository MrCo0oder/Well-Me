package com.codebook.wellme.model.login

sealed class LoginStateUiEvents {
    data class Email(val value: String) : LoginStateUiEvents()
    data class Password(val value: String) : LoginStateUiEvents()
}