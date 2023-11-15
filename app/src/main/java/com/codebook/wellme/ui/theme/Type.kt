package com.codebook.wellme.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.codebook.wellme.R

private val notoSans = FontFamily(
    Font(R.font.notosans_regular, FontWeight.Normal),
    Font(R.font.notosans_semibold, FontWeight.SemiBold),
    Font(R.font.notosans_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = notoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 39.sp,
        letterSpacing = 0.06.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.24.sp,
    ),
    headlineSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.Bold,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.19.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.17.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.14.sp,
    ),
    labelLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.19.sp,
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 15.6.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.14.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 10.sp,
        lineHeight = 10.sp,
        fontFamily = notoSans,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.02.sp,
    ),
)