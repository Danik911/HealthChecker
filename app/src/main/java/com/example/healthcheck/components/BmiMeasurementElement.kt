package com.example.healthcheck.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.models.Diagnosis
import com.example.healthcheck.ui.theme.SMALL_PADDING
import com.example.healthcheck.ui.theme.SMALL_SPACER
import com.example.healthcheck.util.round

@Composable
fun BmiElement(bmiMeasurement: BmiMeasurement) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
           BmiIndex(bmiMeasurement = bmiMeasurement)
            BmiContent(bmiMeasurement = bmiMeasurement)
            
        }

    }
}

@Composable
fun BmiIndex(bmiMeasurement: BmiMeasurement) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = bmiMeasurement.diagnosis.color,

            ),
        modifier = Modifier
            .padding(SMALL_SPACER)
            .size(72.dp),
        elevation = SMALL_PADDING,

        ) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                text = bmiMeasurement.bmiIndex.round(),
                fontWeight = Bold
            )
        }

    }
}

@Composable
fun BmiContent(bmiMeasurement: BmiMeasurement) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Text(
            text = bmiMeasurement.diagnosis.name,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = bmiMeasurement.diagnosis.description,
            style = MaterialTheme.typography.body2
        )
    }
}


@Composable
@Preview
fun BmiMeasurementElementPreview() {
    BmiElement(
        BmiMeasurement(
            0,
            0L,
            diagnosis = Diagnosis.NormalWeight,
            22f
        )
    )
}