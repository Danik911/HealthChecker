package com.example.healthcheck.ui.screens.options_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcheck.R
import com.example.healthcheck.components.OptionsAppBar
import com.example.healthcheck.ui.theme.*
import timber.log.Timber

@Composable
fun OptionsScreen(
    navigateToParamScreen: () -> Unit,
    navigateToListScreen: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        Timber.d("Options Screen")
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            OptionsAppBar(
                onAboutClicked = { /*TODO*/ },
                navigateToList = navigateToListScreen
            )


        },
        content = {
            OptionsContent(navigateToParamScreen = navigateToParamScreen)
        }
    )

}

@Composable
fun OptionsContent(navigateToParamScreen: () -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surfaceColor
    ) {
        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(MEDIUM_PADDING),
                text = stringResource(id = R.string.options_screen_text),
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(LARGE_SPACER))

            Button(onClick = { navigateToParamScreen() }) {
                Text(text = stringResource(id = R.string.bmi_button_text))
            }
        }
    }


}

@Composable
@Preview
fun OptionsScreenPreview() {
    OptionsScreen({}, {})
}