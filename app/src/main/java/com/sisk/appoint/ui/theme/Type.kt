package com.sisk.appoint.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sisk.appoint.R

// Set of Material typography styles to start with
val raleWay = FontFamily(
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_light, FontWeight.Light),
    Font(R.font.raleway_regular, FontWeight.Normal, FontStyle.Italic)
)

val defaultTypography = TextStyle(
    fontFamily = raleWay,
    fontSize = 14.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.W400
)
val typography = Typography(
    bodySmall = defaultTypography.copy(
        fontSize = 12.sp,
    ),
    bodyMedium = defaultTypography.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = defaultTypography.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,

    ),
    labelSmall = defaultTypography.copy(
        fontSize = 11.sp,
        fontWeight = FontWeight.W500
    ),
    labelMedium = defaultTypography.copy(
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
    ),
    labelLarge = defaultTypography.copy(
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500
    ),
    titleSmall = defaultTypography.copy(
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,

    ),
    titleMedium = defaultTypography.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500
    ),
    titleLarge = defaultTypography.copy(
        lineHeight = 28.sp,
        fontSize = 22.sp,
        fontWeight = FontWeight.W400
    ),
    headlineSmall = defaultTypography.copy(
        lineHeight = 32.sp,
        fontSize = 24.sp,
        fontWeight = FontWeight.W400
    ),
    headlineMedium = defaultTypography.copy(
        lineHeight = 36.sp,
        fontSize = 28.sp,
        fontWeight = FontWeight.W400
    ),
    headlineLarge = defaultTypography.copy(
        lineHeight = 40.sp,
        fontSize = 32.sp,
        fontWeight = FontWeight.W400
    ),
    displaySmall = defaultTypography.copy(
        lineHeight = 44.sp,
        fontSize = 36.sp,
        fontWeight = FontWeight.W400
    ),
    displayMedium = defaultTypography.copy(
        lineHeight = 52.sp,
        fontSize = 45.sp,
        fontWeight = FontWeight.W400
    ),
    displayLarge = defaultTypography.copy(
        lineHeight = 64.sp,
        fontSize = 57.sp,
        fontWeight = FontWeight.W400
    )
//    bodyLarge = defaultTypography,
//    titleLarge = TextStyle(fontFamily = raleWay, fontWeight = FontWeight.Medium, fontSize = 22.sp),
//    headlineSmall = TextStyle(fontFamily = raleWay, fontWeight = FontWeight.Bold, fontSize = 18.sp)

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)