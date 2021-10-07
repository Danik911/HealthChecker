package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.components.BmiElement
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.util.RequestState

@Composable
fun ListContent(
    measurements: RequestState<List<BmiMeasurement>>,
    navigateToBmiMeasurement: (bmiId: Int) -> Unit
) {

    if (measurements is RequestState.Success) {
        DisplayMeasurements(
            measurements = measurements.data,
            navigateToBmiMeasurement = navigateToBmiMeasurement
        )
    }


}

@Composable
fun DisplayMeasurements(
    measurements: List<BmiMeasurement>,
    navigateToBmiMeasurement: (bmiId: Int) -> Unit
) {


    LazyColumn {
        items(
            items = measurements,
            key = { item: BmiMeasurement ->
                item.BmiId
            }
        ) { item ->
            BmiElement(
                bmiMeasurement = item
            )
        }
    }
}

@Composable
@Preview
fun DisplayMeasurementsPreview() {

}