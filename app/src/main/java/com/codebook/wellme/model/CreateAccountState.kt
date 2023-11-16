package com.codebook.wellme.model

data class CreateAccountState(
    var email: String = "",
    var password: String = "",
    var name: String = ""
)