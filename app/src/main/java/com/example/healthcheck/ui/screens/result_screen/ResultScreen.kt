package com.example.healthcheck.ui.screens.result_screen


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.example.healthcheck.R
import com.example.healthcheck.data.models.BmiMeasurement
import com.example.healthcheck.data.models.Diagnosis
import com.example.healthcheck.ui.theme.LARGE_SPACER
import com.example.healthcheck.ui.theme.Pink200
import com.example.healthcheck.ui.theme.SMALL_PADDING
import com.example.healthcheck.util.round

@Composable
fun ResultScreen(
    navigateToHomeScreen: () -> Unit,
    navigateToListScreen: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel()
) {

    val latestBmiMeasurement by viewModel.latestBmiMeasurement
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = latestBmiMeasurement) {
        viewModel.getTheLatestTask()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ResultAppBar(
                navigateToHome = navigateToHomeScreen,
                navigateToList = navigateToListScreen
            )


        },
        content = {

            ResultContent(latestBmiMeasurement = latestBmiMeasurement)

        }
    )
}

@Composable
fun ResultContent(latestBmiMeasurement: BmiMeasurement) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Pink200,
        elevation = SMALL_PADDING
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(
                    id = R.string.bmi_measurement_result,
                    latestBmiMeasurement.diagnosis,
                    latestBmiMeasurement.bmiIndex.round()
                ),
                color = Color.Black,
                style = TextStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(LARGE_SPACER))
            CircularResultProgressBar(latestBmiMeasurement = latestBmiMeasurement)
        }

    }

}

@Composable
fun CircularResultProgressBar(
    fontSize: TextUnit = 28.sp,
    radius: Dp = 70.dp,
    strokedWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    latestBmiMeasurement: BmiMeasurement
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = remember { Animatable(0f) }

    val percentage: Float = latestBmiMeasurement.diagnosis.angle
    val color: Color = latestBmiMeasurement.diagnosis.color
    val result = latestBmiMeasurement.bmiIndex


    LaunchedEffect(percentage) {
        currentPercentage.animateTo(
            percentage,
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDelay
            )
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(
                    strokedWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

        }
        Text(
            text = result.round(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
@Preview
fun ContentResultScreenPreview() {
    ResultContent(
        BmiMeasurement(
            0,
            "",
            0L,
            diagnosis = Diagnosis.NormalWeight,
            0f
        )
    )

}
/*@Composable
@Preview
fun ResultScreenPreview() {
    ResultContent()
}*/

