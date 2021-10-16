package com.example.healthcheck.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Pink500 = Color(0xFFf48fb1)
val Pink200 = Color(0xFFffc1e3)
val Pink700 = Color(0xFFbf5f82)

val Purple500 = Color(0xFF9575cd)
val Purple200 = Color(0xFFc7a4ff)
val Purple700 = Color(0xFF65499c)
val MediumGray = Color(0xFF9C9C9C)

val HealthyWeight = Color(0xFF00C980)
val LowOverWeight = Color(0xFFFFC114)
val Obesity = Color(0XFFFF4646)

val Dots = Color(0xFF03045e)

val Colors.surfaceColor: Color
    @Composable
    get() = if (isLight) Pink200 else Purple700

val Colors.splashScreenBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple200 else Color.Black