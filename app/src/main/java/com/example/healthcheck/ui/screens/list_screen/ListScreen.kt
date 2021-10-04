package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListScreen(
    //navigateToBmiMeasurement: (BmiId: Int) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllBmiMeasurements()
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = { TODO()},

        content = { TODO()}
    )

}


