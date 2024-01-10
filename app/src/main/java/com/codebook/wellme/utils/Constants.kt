package com.codebook.wellme.utils

object Constants {
    const val PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])^(?=.*[0-9!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\/-])(?=\\S+).+.{7,}$"
    const val UPPER_LOWER_PATTERN = "^(?=.*[a-z])(?=.*[A-Z]).+\$"
    const val AT_LEAST_NUM_OR_CHAR_PATTERN = "^(?=.*[0-9!@#\$%^&*()_+{}\\[\\]:;<>,.?~\\/-]).+\$"

    const val USER_NAME = "USER_NAME"
    const val USER_MAIL = "USER_MAIL"

    const val ACTION_SERVICE_START = "ACTION_SERVICE_START"
    const val ACTION_SERVICE_STOP = "ACTION_SERVICE_STOP"
    const val ACTION_SERVICE_CANCEL = "ACTION_SERVICE_CANCEL"

    const val STOPWATCH_STATE = "STOPWATCH_STATE"

    const val NOTIFICATION_CHANNEL_ID = "STOPWATCH_NOTIFICATION_ID"
    const val NOTIFICATION_CHANNEL_NAME = "STOPWATCH_NOTIFICATION"
    const val NOTIFICATION_ID = 10

    const val CLICK_REQUEST_CODE = 100
    const val CANCEL_REQUEST_CODE = 101
    const val STOP_REQUEST_CODE = 102
    const val RESUME_REQUEST_CODE = 103
}