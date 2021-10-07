package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListScreen(
    navigateToBmiMeasurement: (BmiId: Int) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllBmiMeasurements()
    }

    val allBmiMeasurement by viewModel.allBmiMeasurement.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {},

        content = {
           ListContent(
               measurements = allBmiMeasurement,
               navigateToBmiMeasurement = navigateToBmiMeasurement
           )
        }
    )

}


