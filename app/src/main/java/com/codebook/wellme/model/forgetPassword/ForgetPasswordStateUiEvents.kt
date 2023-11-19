package com.codebook.wellme.model.forgetPassword

sealed class ForgetPasswordStateUiEvents {
    data class Email(val value: String) : ForgetPasswordStateUiEvents()
}