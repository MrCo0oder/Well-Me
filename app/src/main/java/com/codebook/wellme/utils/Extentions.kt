package com.codebook.wellme.utils

import android.util.Patterns
import java.util.Calendar
import java.util.regex.Pattern

fun String.validateEmail(): String? {
    return if (this.isEmpty()) {
        null
    } else if (!Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), this)) {
        "Please enter a valid email"
    } else {
        null
    }
}

fun String.validatePassword(withMessage: Boolean = true): String? {
    return if (this.isEmpty()) {
        null
    } else if (this.length < 8) {
        if (withMessage) ("Password must have at least 8 characters") else "  "
    } else {
        null
    }
}

fun String.validateWithRegex(regex: String): Boolean {
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}
fun dateValidator(timeInMillis: Long): Boolean {
    val endCalenderDate = Calendar.getInstance()
    endCalenderDate.timeInMillis = timeInMillis
    endCalenderDate.set(Calendar.DATE, Calendar.DATE )
    return timeInMillis > Calendar.getInstance().timeInMillis && timeInMillis < endCalenderDate.timeInMillis
}
fun formatTime(seconds: String, minutes: String, hours: String): String {
    return "$hours:$minutes:$seconds"
}

fun Int.pad(): String {
    return this.toString().padStart(2, '0')
}