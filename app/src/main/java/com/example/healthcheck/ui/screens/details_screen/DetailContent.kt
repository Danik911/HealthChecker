package com.example.healthcheck.ui.screens.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.components.BmiContent
import com.example.healthcheck.components.BmiElement
import com.example.healthcheck.components.BmiIndex
import com.example.healthcheck.components.EmptyBmiElement
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.models.Diagnosis
import com.example.healthcheck.ui.theme.Pink200

@Composable
fun DetailsContent(bmiMeasurement: BmiMeasurement) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        BmiElement(
            bmiMeasurement = bmiMeasurement,
            bmiContent = {
                EmptyBmiElement(
                    bmiContent = { BmiContent(bmiMeasurement = bmiMeasurement) },
                    bmiIndex = { BmiIndex(bmiMeasurement = bmiMeasurement) }
                )
            }
        )

    }

}


@Composable
@Preview
fun DetailsContentPreview() {
    DetailsContent(
        BmiMeasurement(
            1,
            1L,
            Diagnosis.NormalWeight,
            22.4f
        )
    )
}