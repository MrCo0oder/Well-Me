package com.codebook.wellme.utils

object Constants {
    const val PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])^(?=.*[0-9!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\/-])(?=\\S+).+.{7,}$"
    const val UPPER_LOWER_PATTERN = "^(?=.*[a-z])(?=.*[A-Z]).+\$"
    const val AT_LEAST_NUM_OR_CHAR_PATTERN = "^(?=.*[0-9!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\/-]).+\$"

    const val USER_NAME = "USER_NAME"
    const val USER_MAIL = "USER_MAIL"

}