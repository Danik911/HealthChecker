package com.example.healthcheck.ui.screens.list_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToBmiMeasurement: (BmiId: Int) -> Unit,
    navigateToHome: () -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllBmiMeasurements()
    }

    val event by viewModel.event
    val allBmiMeasurement by viewModel.allBmiMeasurement.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
                 ListAppBar(navigateToHome = navigateToHome, viewModel = viewModel)


        },

        content = {
           ListContent(
               measurements = allBmiMeasurement,
               navigateToBmiMeasurement = navigateToBmiMeasurement,
              viewModel = viewModel,
               onSwipeToDelete = {bmiMeasurement ->
                   viewModel.deleteBmiMeasurement(bmiMeasurement)
               }
           )
        }
    )

}
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
@Preview
private fun ListScreenPreview() {
    ListScreen(navigateToBmiMeasurement = {}, navigateToHome = { })
}


