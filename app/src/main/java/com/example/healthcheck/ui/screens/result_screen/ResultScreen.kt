package com.example.healthcheck.ui.screens.result_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultScreen(
    navigateToHomeScreen: () -> Unit,
    viewModel: ResultViewModel = hiltViewModel()
) {

    val result = viewModel.resultState.value





    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ResultAppBar(navigateToHome = navigateToHomeScreen)


        },
        content = {
            ResultContent(result = result.toString())
        }
    )
}

@Composable
fun ResultContent(result: String) {
    Text(text = result)
}

