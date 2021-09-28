package com.example.healthcheck.data.models

import androidx.compose.ui.graphics.Color
import com.example.healthcheck.ui.theme.HealthyWeight
import com.example.healthcheck.ui.theme.LowOverWeight
import com.example.healthcheck.ui.theme.Obesity

enum class MassResult(color: Color) {
    HEALTHY_WEIGHT(HealthyWeight),
    LOW_OVER_WEIGHT(LowOverWeight),
    OBESITY(Obesity)
}