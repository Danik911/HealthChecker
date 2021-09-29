package com.example.healthcheck.ui.screens.param_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.healthcheck.R
import com.example.healthcheck.components.OptionsAppBar
import com.example.healthcheck.ui.theme.LARGE_SPACER
import com.example.healthcheck.ui.theme.surfaceColor

@Composable
fun ParamScreen(

    viewModel: ParamViewModel = hiltViewModel()

) {

    val weight by viewModel.weightState
    val high by viewModel.highState
    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ParamAppBar {

            }
        },
        content = {
            ParamContent(
                weight = weight,
                high = high,
                onHighChange = {high ->
                    viewModel.highState.value = high
                },
                onWeightChange = {weight ->
                    viewModel.weightState.value = weight
                }
            )
        }
    )

}

@Composable
fun ParamAppBar(onHomeClicked: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onHomeClicked() }) {
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
    onWeightChange: (String) -> Unit
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
            imeAction = ImeAction.Done
        )
    )
}

@Composable
@Preview
private fun ParamAppBarPreview() {
    ParamScreen(

    )
}

@Composable
@Preview
private fun SharedTextFieldPreview() {
    SharedTextField("Weigh", {}, "")
}