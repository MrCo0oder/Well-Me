package com.codebook.wellme.model.signup

data class CreateAccountState(
    var email: String = "",
    var password: String = "",
    var name: String = ""
)