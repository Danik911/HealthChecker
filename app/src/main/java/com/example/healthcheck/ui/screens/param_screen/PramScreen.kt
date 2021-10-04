package com.example.healthcheck.ui.screens.param_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthcheck.R
import com.example.healthcheck.ui.theme.LARGE_SPACER
import com.example.healthcheck.ui.theme.surfaceColor
import com.example.healthcheck.util.isValidNumber
import timber.log.Timber

@Composable
fun ParamScreen(
    viewModel: ParamViewModel = hiltViewModel(),
    navigateToResultScreen: (Long) -> Unit,
    navigateToHome: () -> Unit
) {

    val weight by viewModel.weightState
    val high by viewModel.highState
    val scaffoldState = rememberScaffoldState()

    val currentMeasurement by viewModel.currentMeasurement.collectAsState()

    val context = LocalContext.current


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ParamAppBar(navigateToHome = navigateToHome)


        },
        content = {
            ParamContent(viewModel = viewModel,
                weight = weight,
                high = high,
                onHighChange = { high ->
                    viewModel.highState.value = high
                },
                onWeightChange = { weight ->
                    viewModel.weightState.value = weight
                },
                onSubmitClicked = {
                    if (weight.isNotBlank() && high.isNotBlank()) {
                        if (weight.isValidNumber() && high.isValidNumber()) {
                            viewModel.calculateBmiIndex()
                            viewModel.addBmiMeasurement()
                            navigateToResultScreen(currentMeasurement.timestamp)

                        } else {
                            displayToastInvalid(context = context)
                        }
                    } else {
                        displayToastEmpty(context = context)
                    }

                }
            )
        }
    )
}

@Composable
fun ParamAppBar(navigateToHome: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigateToHome() }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.home_icon)
                )
            }
        },
        title = { Text(text = stringResource(id = R.string.param_screen_app_bar)) }

    )

}

@Composable
fun ParamContent(
    weight: String,
    high: String,
    onHighChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onSubmitClicked: (result: Float) -> Unit,
    viewModel: ParamViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surfaceColor
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeightTextField(
                weight = weight,
                onWeightChange = onWeightChange
            )

            Spacer(modifier = Modifier.height(LARGE_SPACER))

            HighTextField(
                high = high,
                onHighChange = onHighChange
            )
            Spacer(modifier = Modifier.height(LARGE_SPACER))

            SubmitButton(onSubmitClicked = onSubmitClicked, viewModel = viewModel)

        }

    }
}

@Composable
fun WeightTextField(
    weight: String,
    onWeightChange: (String) -> Unit
) {
    SharedTextField(
        text = weight,
        onTextChange = onWeightChange,
        hint = stringResource(id = R.string.weight_hint)
    )
}

@Composable
fun HighTextField(
    high: String,
    onHighChange: (String) -> Unit
) {
    SharedTextField(
        text = high,
        onTextChange = onHighChange,
        hint = stringResource(id = R.string.high_hint)
    )
}

@Composable
fun SharedTextField(
    text: String,
    onTextChange: (String) -> Unit,
    hint: String
) {
    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        placeholder = {
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = hint,
                color = Color.White
            )
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        )
    )
}

@Composable
fun SubmitButton(onSubmitClicked: (result: Float) -> Unit, viewModel: ParamViewModel) {

    Button(onClick = { onSubmitClicked(viewModel.bmiResult.value) }) {
        Text(text = stringResource(id = R.string.submit_button))
    }
}

fun displayToastEmpty(context: Context) {
    Toast.makeText(
        context,
        R.string.toast_empty_fields,
        Toast.LENGTH_SHORT
    ).show()
}

fun displayToastInvalid(context: Context) {
    Toast.makeText(
        context,
        R.string.toast_invalid_parameters_fields,
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
@Preview
private fun ParamScreenPreview() {
    //val testViewModel = ParamViewModel()
    //ParamScreen(testViewModel, {}, {})
}

/*
@Composable
@Preview
private fun SharedTextFieldPreview() {
    SharedTextField("Weigh", {}, "")
}*/
