package com.codebook.wellme.model

sealed class CreateAccountStateUiEvents {
    data class Email(val value: String) : CreateAccountStateUiEvents()
    data class Password(val value: String) : CreateAccountStateUiEvents()
    data class Name(val value: String) : CreateAccountStateUiEvents()
}